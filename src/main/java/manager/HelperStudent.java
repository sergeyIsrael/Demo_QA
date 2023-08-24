package manager;

import models.Gender;
import models.Hobby;
import models.StudentDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public interface HelperStudent extends HelperBase {

    default void selectForms(){
        if(isElementPresent(By.id("adplus-anchor"))){
            hideAds();
        }
        click(By.xpath("//div[@class='category-cards']/div[2]"));
    }

    default void selectPracticeForm(){
        click(By.xpath("//span[.='Practice Form']"));
    }

    default void fillForm(StudentDTO studentDTO){
        type(By.id("firstName"), studentDTO.getFirstName());
        type(By.id("lastName"), studentDTO.getLastName());
        type(By.id("userEmail"), studentDTO.getEmail());
        selectGender(studentDTO.getGender());
        type(By.id("userNumber"), studentDTO.getPhone());
        typeBDaySelect(studentDTO.getBirthday());
//        typeBDay(studentDTO.getBirthday());
//        type(By.id("dateOfBirthInput"), studentDTO.getBirthday());
        addSubject(studentDTO.getSubject());
        selectHobby(studentDTO.getHobbies());
        uploadPicture();
        type(By.id("currentAddress"), studentDTO.getAddress());
        typeState(studentDTO.getState());
        typeCity(studentDTO.getCity());
    }

    default void typeBDay(String birthday){
        WebElement date = wd.findElement(By.id("dateOfBirthInput"));
        date.click();
        String os = System.getProperty("os.name");
        System.out.println(os);
        if(os.startsWith("Win")){
            date.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        } else {
            date.sendKeys(Keys.chord(Keys.COMMAND, "a"));
        }
        date.sendKeys(birthday);
        date.sendKeys(Keys.ENTER);
    }

    default void typeBDaySelect(String birthday){
        // 06 29 2000
        String[] date = birthday.split(" ");
        click(By.id("dateOfBirthInput"));
        new Select(wd.findElement(By.className("react-datepicker__month-select"))).selectByValue("" + (Integer.parseInt(date[0]) - 1));
//        System.out.println(date[0]);
        new Select(wd.findElement(By.className("react-datepicker__year-select"))).selectByValue(date[2]);
        String day = String.format("//div[.='%s']", date[1]);
        List<WebElement> days = wd.findElements(By.xpath(day));
        if(days.size() > 1 && Integer.parseInt(date[1]) > 15){
            days.get(1).click();
        } else {
            days.get(0).click();
        }
//        click(By.xpath("//div[.='" + date[1] + "']"));
    }

    default void selectGender(Gender gender){
        if(gender.equals(Gender.MALE)){
            click(By.xpath("//label[@for='gender-radio-1']"));
        } else if (gender.equals(Gender.FEMALE)){
            click(By.xpath("//label[@for='gender-radio-2']"));
        } else {
            click(By.xpath("//label[@for='gender-radio-3']"));
        }
    }

    default void addSubject(String subjects){
        String[] split = subjects.split(",");
        String locator = "subjectsInput";
        click(By.id(locator));
        for(String subject : split){
            wd.findElement(By.id(locator)).sendKeys(subject);
            wd.findElement(By.id(locator)).sendKeys(Keys.ENTER);
        }
    }

    default void selectHobby(List<Hobby> hobbies){
        for(Hobby hobby : hobbies){
            switch (hobby){
                case SPORTS:
                    click(By.xpath("//label[@for='hobbies-checkbox-1']"));
                    break;
                case READING:
                    click(By.xpath("//label[@for='hobbies-checkbox-2']"));
                    break;
                case MUSIC:
                    click(By.xpath("//label[@for='hobbies-checkbox-3']"));
                    break;
            }
        }
    }

    default void uploadPicture(){
        wd.findElement(By.id("uploadPicture")).sendKeys("/Users/MacbookPro/GitHub/Demo_QA/student.jpeg");
    }

    default void typeState(String state){
        wd.findElement(By.id("react-select-3-input")).sendKeys(state);
        wd.findElement(By.id("react-select-3-input")).sendKeys(Keys.ENTER);
    }

    default void typeCity(String city){
        wd.findElement(By.id("react-select-4-input")).sendKeys(city);
        wd.findElement(By.id("react-select-4-input")).sendKeys(Keys.ENTER);
    }

    default void submit(){
        click(By.id("submit"));
    }



}
