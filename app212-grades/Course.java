import java.util.ArrayList;
/**
 * This class stores information about a course
 * that enrolled students may want to complete
 *
 * @author Derek Peacock and Nicholas Day
 * Modified by Liam Smith
 * version 1.1 16/10/2021
 */
public class Course
{
    // maximum number of modules
    public final static int MAXN_MODULES = 4;
    // array of modules
    public ArrayList<Module> modules;
    // array of marks
    public ArrayList<ModuleMark> marks;
    // array of enrolled students
    public ArrayList<Student> students;
    
    // instances of students
    public Student student1;
    public Student student2;
    public Student student3;
    public Student student4;
    
    // course code
    public String code;
    // course title
    private String title;
    // final grade for course
    private Grades finalGrade;
    
    // instances of course modules
    private Module module1;
    private Module module2;
    private Module module3;
    private Module module4;

    public Course()
    {
        this("MT1CYS1", "BSc Cyber Security");
    }
    
    /**
     * Constructor for objects of class Course
     */
    public Course(String code, String title)
    {
        // initialise instance variables
        this.code = code;
        this.title = title;
        
        // creates modules array
        modules  = new ArrayList<Module>();
        // creates students array
        students = new ArrayList<Student>();
    }

    /**
     * Create four modules and add them to the
     * modules list for testing purposes.  These
     * must be your four modules.
     */
    public void createModules()
    {    
        Module module1 = new Module("CO450", "Computer Architectures");
        Module module2 = new Module("CO452", "Programming Concepts");
        Module module3 = new Module("CO454", "Digital Technologies");
        Module module4 = new Module("CO456", "Web Development");
        
        // adds modules to array
        modules.add(module1);
        modules.add(module2);
        modules.add(module3);
        modules.add(module4);
    }
    
    /**
     * Adds a module to the course unless maximum number of modules has been reached.
     * 
     */
    public void addModule(Module module)
    {
        if(modules.size() < MAXN_MODULES)
        {
            modules.add(module);
        }
        
        else {
            System.out.println("The maximum number of modules have been added");
            // displays error message
        }
    }
    
    /**
     * Add a list of students to the course.
     * 
     */
    
    public void addStudents()
    {
        student1 = new Student("Adam", 11111111);
        student2 = new Student("Becky", 11111112);
        student3 = new Student("Chad", 11111113);
        student4 = new Student("David", 11111114);
        
        // adds students to array
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
    }
    
    /**
     * Prints an enrolment list for the course
     */
    
    public void printEnrolled()
    {
        for (int i = 0; i < students.size() ; i++)
        {
            System.out.println(students.get(i).name + " " + students.get(i).id);
        }
    }
    
    /**
     * Checks how the marks compare to the values associated in the Grades Enum.
     */
    public Grades convertToGrade(int mark)
    {
        if(mark == Grades.NS.getValue()){
            return Grades.NS;
        }
        
        else if(mark > Grades.NS.getValue() && mark <= Grades.F.getValue()){
            return Grades.F;
        }
        
        else if(mark > Grades.F.getValue() && mark <= Grades.D.getValue()){
            return Grades.D;
        }
        
        else if(mark > Grades.D.getValue() && mark <= Grades.C.getValue()){
            return Grades.C;
        }
        
        else if(mark > Grades.C.getValue() && mark <= Grades.B.getValue()){
            return Grades.B;
        }
        
        else if(mark > Grades.B.getValue() && mark <= Grades.A.getValue()){
            return Grades.A;
        }
        
        return Grades.NS;
    }
    
    /**
     * Calculate the average mark from the four module marks
     * and convert that into a final grade.
     */
    public Grades calculateGrade(ArrayList<ModuleMark> marks)
    {
        int averageMark = 0;
        Grades grade;
        
        for(int i=0; i < marks.size(); i++){
            averageMark = averageMark + marks.get(i).mark;
        }
        
        averageMark = averageMark / marks.size();
        
        grade = convertToGrade(averageMark);
        
        return grade;
    }
    
    /**
     * Prints out the details of a course and the
     * four modules
     */
    public void print()
    {
        System.out.println();
        System.out.println(" Course Code: " + this.code);
        System.out.println(" Title: " +  this.title);
        System.out.println();
        
        printModules();
    }
    
    /**
     * Print the course's four modules
     */
    public void printModules()
    {
        //checks that the modules array is not empty
        if(modules.size() == 0){
            System.out.println("No modules have been added to this course");
        }
        else {
            // j will display the correct index for each module
            int j=1;
            for(int i=0; i < modules.size(); i++){
            System.out.println(" Module " + j + ": " + modules.get(i).code
            + ": " + modules.get(i).title);
            j++;
        }
        
    }
    }
}
