
/**
 * Write a description of class Test here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Test
{
    // instance variables - replace the example below with your own
    private Student student;
    private Course course;
    private Module module;
    private Module module1;
    private Module module2;
    private Module module3;
    private Module module4;
    
    private ModuleMark moduleMa1;
    private ModuleMark moduleMa2;
    private ModuleMark moduleMa3;
    private ModuleMark moduleMa4;
    
    /**
     * Constructor for objects of class Test
     */
    public Test()
    {
        // initialise instance variables
        student = new Student();
        course = new Course();
        student.enrol(course);
        
        Module module1 = new Module("CO450", "Computer Architectures");
        Module module2 = new Module("CO452", "Programming Concepts");
        Module module3 = new Module("CO454", "Digital Technologies");
        Module module4 = new Module("CO456", "Web Development");
        
        course.addModule(module1);
        course.addModule(module2);
        course.addModule(module3);
        course.addModule(module4);
        
        //course.print();
        
        moduleMa1 = new ModuleMark(module1);
        moduleMa2 = new ModuleMark(module2);   
        moduleMa3 = new ModuleMark(module3);   
        moduleMa4 = new ModuleMark(module4);
        
        student.addMark(moduleMa1);
        student.addMark(moduleMa2);
        student.addMark(moduleMa3);
        student.addMark(moduleMa4);
        
        //student.awardMark("CO450", 0);
        //student.awardMark("CO452", 0);
        //student.awardMark("CO454", 0);
        //student.awardMark("CO456", 0);
        
        student.awardTestMarks(moduleMa1, 40);
        student.awardTestMarks(moduleMa2, 50);
        student.awardTestMarks(moduleMa3, 60);
        student.awardTestMarks(moduleMa4, 70);
        
        student.printTranscript();
        
        
        
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void sampleMethod(int y)
    {
        // put your code here
        
    }
}
