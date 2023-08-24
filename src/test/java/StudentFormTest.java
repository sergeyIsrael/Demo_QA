
import manager.HelperStudent;
import models.Gender;
import models.Hobby;
import models.StudentDTO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class StudentFormTest extends TestBase implements HelperStudent {

    @BeforeMethod
    public void precondition(){
        selectForms();
        selectPracticeForm();
    }

    @Test
    public void studentFormPositive(){
        List<Hobby> hobbies = new ArrayList<>();
        hobbies.add(Hobby.MUSIC);
        hobbies.add(Hobby.READING);

        StudentDTO studentDTO = StudentDTO.builder()
                .firstName("Sam")
                .lastName("Smith")
                .email("ddd@mail.com")
                .gender(Gender.MALE)
                .phone("098765432")
                .birthday("8 3 2000")
                .subject("Maths,Physics")
                .hobbies(hobbies)
                .address("Respubliki 3")
                .state("NCR")
                .city("NY")
                .build();

        hideFooter();
        hideDiv();
        fillForm(studentDTO);
        submit();
    }


}
