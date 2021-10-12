import java.util.ArrayList;
/**
 * This class stores information about a course
 * that enrolled students may want to complete
 *
 * @author Derek Peacock and Nicholas Day
 * @version 0.1 11/Sep/2020
 */
public class Course
{
    public final static int MAXN_MODULES = 4;
    
    public ArrayList<Module> modules;
    
    public ArrayList<ModuleMark> marks;
    
    public String code;
    private String title;
    
    private Grades finalGrade;
    
    private Module module;
    public Module module1;
    private Module module2;
    private Module module3;
    private Module module4;

    

     
    public Course()
    {
        this("G400", "BSc Computing");
    }
    
    /**
     * Constructor for objects of class Course
     */
    public Course(String code, String title)
    {
        // initialise instance variables
        this.code = code;
        this.title = title;
        
        modules  = new ArrayList<Module>();
        //createModules();
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
        Module module3 = new Module("CO454", "Digital Technologies and Professional Practice");
        Module module4 = new Module("CO456", "Web Development");
        
        this.module1 = module1;
        this.module2 = module2;
        this.module3 = module3;
        this.module4 = module4;
        
        modules.add(module1);
        modules.add(module2);
        modules.add(module3);
        modules.add(module4);
    }
    
    public void addModule(Module module)
    {
        if(modules.size() < MAXN_MODULES)
        {
            modules.add(module);
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
        
        System.out.println(averageMark);
        
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
        int j=1;
        for(int i=0; i < modules.size(); i++){
        System.out.println(" Module " + j + ": " + modules.get(i).code
        + ": " + modules.get(i).title);
        j++;
    }
    }
}
