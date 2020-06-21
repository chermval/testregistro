package com.example.test.testregistro.api.business;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.SplittableRandom;

import org.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.test.testregistro.api.model.RegistryDTO;

@Service
public class AdditionalRegistryServiceImpl implements AdditionalRegistryService{

    public boolean validateData(RegistryDTO data){
        return ( null != data.getNames().trim() && data.getNames().trim().length() != 0
                && null != data.getLastNames().trim() && data.getLastNames().trim().length() != 0  
                && validBirthDay(data.getBirthday()) );
    }

    //method for validate date
    public boolean validBirthDay(String date){
        LocalDate dateValid = validateFormatDate(date);

        if (dateValid == null || !validatePastDate(dateValid)){
            return false;
        }
        return true;
    }

    //validate format of date yyyy-mm-dd
    public LocalDate validateFormatDate(String date){

        LocalDate dateFormatted;
        try {
            dateFormatted = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            dateFormatted = null;
        }
        
        return dateFormatted;
    }

    //Validate that date is not future
    public boolean validatePastDate(LocalDate birthday){
        LocalDate today = LocalDate.now();
        return today.isAfter(birthday);
    }

    //method for calculate years old 
    public RegistryDTO getYearsOld(RegistryDTO data){
        //get actual day
        LocalDate today = LocalDate.now();
        LocalDate dateBirthday = LocalDate.parse(data.getBirthday());  
        
        //calculate years old
        data.setYearsOld( Period.between(dateBirthday, today).getYears() );
        return data;
    }

    //method for get comment about birthday
    public RegistryDTO getComment(RegistryDTO data){
        long dayLeftBirthday = getDayLeftForBitrhday(data);
        

        if ( todayIsBithday(data) ){
            // if is birthday get congratulation message and randompoem
            String name = data.getNames().trim().split("\\s+")[0];
            String poem = getRandomPoem();

            data.setMessage("Feliz cumpleaños "+name+"! \n Poema: "+poem);

        }else{
            // if not birthday get day left for birthday
            data.setMessage("Faltan "+dayLeftBirthday+" días para tu cumpleaños!");
        }

        return data;
    }

    //method for validate if today is birthday
    public Boolean todayIsBithday(RegistryDTO data){
        LocalDate dateBirthday = LocalDate.parse(data.getBirthday());  
    
        MonthDay birthday = MonthDay.of(dateBirthday.getMonth(), dateBirthday.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(LocalDate.now());
    
        if (currentMonthDay.equals(birthday)) {
          return true;
        }
        return false;
    }

    //method to get day left for birthday
    public long getDayLeftForBitrhday(RegistryDTO data){
        LocalDate today = LocalDate.now();
        LocalDate dateBirthday = LocalDate.parse(data.getBirthday());

        //calculte next birthday
        LocalDate nextBirthDay = dateBirthday.withYear(today.getYear());
        //if birthday date has occurred this year, use next year.
        nextBirthDay = (nextBirthDay.isBefore(today) || nextBirthDay.isEqual(today)) ? nextBirthDay.plusYears(1) : nextBirthDay;
        
        return ChronoUnit.DAYS.between(today, nextBirthDay); 
    }

    //method to get random poem from api poemist
    public String getRandomPoem(){
        final String url = "https://www.poemist.com/api/v1/randompoems";
     
        RestTemplate restTemplate = new RestTemplate();
        // get list of poems 
        JSONArray poems = new JSONArray( restTemplate.getForObject(url, String.class));
        int randomNumber = new SplittableRandom().nextInt(0, poems.length()); 
        //get random poem from list of poems
        String poem = poems.getJSONObject(randomNumber).getString("content");
        
        return poem;
    }
}
