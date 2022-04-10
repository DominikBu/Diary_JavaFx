package com.example.lab7;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.function.Function;

public class HelloController implements Initializable {

    private ClassContainer group;
    private Student student_global;
    private Class subject_global;
    private Grade grade_global;
    private Class subject_add_global;
    Stage stage;

    @FXML
    private Button exportCsvButton;

    @FXML
    private Button importCsvButton;

    @FXML
    private Button serializeButton;

    @FXML
    private Button deserializeButton;

    @FXML
    private Button signStudentButton;

    @FXML
    private Button changeConditionButton;

    @FXML
    private Button addGradeButton;

    @FXML
    private Button addStudentButton;

    @FXML
    private Button addSubjectButton;

    @FXML
    private Button removeFromSubjectButton;

    @FXML
    private Button changeStudentInfoButton;

    @FXML
    private Button changeSubjectInfoButton;

    @FXML
    private Button deleteGradeButton;

    @FXML
    private Button deleteStudentButton;

    @FXML
    private Button deleteSubjectButton;

    @FXML
    private TextField fieldOur;

    @FXML
    private ChoiceBox<Class> allSubjects;

    @FXML
    private TableColumn<Student, String> names;

    @FXML
    private TableColumn<Student, String> surname;

    @FXML
    private TableView<Student> tableStudent;

    @FXML
    private Label global_student_con;

    @FXML
    private Label global_student_con1;

    @FXML
    private Label averageGradeSubject;

    @FXML
    private Label averageGradeSubject1;

    @FXML
    private Label global_student_name;

    @FXML
    private Label global_student_surname;

    @FXML
    private Label global_student_year;

    @FXML
    private Label global_student_year1;

    @FXML
    private Label global_student_points;

    @FXML
    private Label global_student_points1;

    @FXML
    private TableView<Subject> tableSubject;

    @FXML
    private TableColumn<Subject, String> subject;

    @FXML
    private TableColumn<Grade, Integer> grades;

    @FXML
    private TableColumn<Subject, Double> gradeColumn;

    @FXML
    private TableView<Grade> tableGrades;

    @FXML
    private GridPane allGrid;



    ObservableList<Student> listStudent = FXCollections.observableArrayList();
    ObservableList<Subject> listSubject = FXCollections.observableArrayList();
    ObservableList<Grade> listGrades = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        group = new ClassContainer();
        subject_global = null;
        student_global = null;
        grade_global = null;

        DataGenerator d = new DataGenerator();
        group = d.getGroup();

        createTableStudent();
        createTableSubject();
        createTableGrades();
        createChoiceBoxSubjects();

        importCsv();



        tableStudent.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                selectStudent();
            }
        });

        tableSubject.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                selectSubject();
            }
        });

        tableGrades.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                selectGrade();
            }
        });

        allSubjects.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Class> observable, Class oldValue, Class newValue) -> {
            subject_add_global=newValue;
        });

        addStudentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addStudentEvent();
            }
        });

        deleteStudentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteStudentEvent();
            }
        });

        changeStudentInfoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeStudentInfo();
            }
        });

        addSubjectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addSubjectEvent();
            }
        });

        deleteSubjectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteSubject();
            }
        });

        changeSubjectInfoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeSubjectInfo();
            }
        });

        addGradeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addGradeEvent();
            }
        });

        deleteGradeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteGradeEvent();
            }
        });

        changeConditionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeCondition();
            }
        });

        signStudentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                signUpStudent();
            }
        });

        removeFromSubjectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                removeFromSubject();
            }
        });

        tableSubject.setRowFactory((tableView) -> {
            return new TooltipTableRow<Subject>((Subject s) -> {
                String text = toolTipSubject(s.getCl().getId());
                return text;
            });
        });

        serializeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(Student.getIds());
                serialization();
            }
        });

        deserializeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deserialization();

            }
        });

        exportCsvButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                exportCsv();
            }
        });

        importCsvButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                importCsv();
            }
        });

    }

    public void exitEvent( ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setContentText("Do you want to save before exiting?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            exportCsv();
            stage = (Stage) allGrid.getScene().getWindow();
            stage.close();
        }
    }

    public void hi()
    {
        System.out.println("hiihihihiihh");
    }

    public void exportCsv()
    {
        try {
            csvExportClass();
            csvExportStudent();
            csvExportGrades();
        }catch(Exception e) {
            dialogBoxWarning("Error, export csv failed!");
        }
    }

    public void csvExportStudent() throws Exception
    {

        FileWriter fw = new FileWriter("Student.csv", false);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        boolean names = false;

        Map<Integer, Field> list = new HashMap<>();

        Student w = new Student();
        Field[] allFields = w.getClass().getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            CsvExport annotation = field.getAnnotation(CsvExport.class);
            if(annotation == null)
                continue;
            else
            {
                names = true;
                list.put(annotation.nr(), field);
            }
        }

        if(names) {
            for (Field f : list.values())
                pw.print(f.getName() + ";");
            pw.println("id_subject;");
        }else
            pw.println();



        if(group.getM().values() != null)
            for (Class cl : group.getM().values())
            {
                if(cl.getStudentlist() != null)
                    for(Student s : cl.getStudentlist())
                    {

                        if(names)
                        {
                            for (Field f : list.values()) {
                                String str = f.getName();
                                String cap = "get" + str.substring(0, 1).toUpperCase() + str.substring(1);
                                Method m = s.getClass().getDeclaredMethod(cap);

                                pw.print(m.invoke(s) + ";" );
                            }
                            pw.println(cl.getId() + ";");
                        }else
                            pw.println(s.getId() + ";" + s.getName() + ";" + s.getSname() + ";" + s.getYear() + ";" + cl.getId() + ";");
                    }
            }

        pw.flush();
        pw.close();
    }

    public void csvExportClass() throws Exception
    {
        FileWriter fw = new FileWriter("Class.csv", false);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        boolean names = false;

        Map<Integer, Field> list = new HashMap<>();

        Class w = new Class();
        Field[] allFields = w.getClass().getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            CsvExport annotation = field.getAnnotation(CsvExport.class);
            if(annotation == null)
                continue;
            else
            {
                names = true;
                list.put(annotation.nr(), field);
            }
        }

        if(names) {
            for (Field f : list.values())
                pw.print(f.getName() + ";");
        }
        pw.println();

        if(group.getM().values() != null)
            for (Class cl : group.getM().values())
            {
                if(names)
                {
                    for (Field f : list.values()) {
                        String str = f.getName();
                        String cap = "get" + str.substring(0, 1).toUpperCase() + str.substring(1);
                        Method m = cl.getClass().getDeclaredMethod(cap);

                        pw.print(m.invoke(cl) + ";" );
                    }
                    pw.println();
                }else
                    pw.println(cl.getId() + ";" + cl.getGroup_name() + ";" + cl.getMax_student() + ";");
            }

        pw.flush();
        pw.close();
    }

    public void csvExportGrades() throws Exception
    {

        FileWriter fw = new FileWriter("Grade.csv", false);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        boolean names = false;

        Map<Integer, Field> list = new HashMap<>();

        Grade w = new Grade();
        Field[] allFields = w.getClass().getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            CsvExport annotation = field.getAnnotation(CsvExport.class);
            if(annotation == null)
                continue;
            else
            {
                names = true;
                list.put(annotation.nr(), field);
            }
        }

        if(names) {
            for (Field f : list.values())
                pw.print(f.getName() + ";");
        }
        pw.println();

        if(group.getM().values() != null)
            for (Class cl : group.getM().values())
            {
                if(cl.getStudentlist() != null)
                    for(Student s : cl.getStudentlist())
                    {
                        s.setStudent_con(StudentCondition.OCZEKIWANY, cl.getId());
                        if(s.getGrades(cl.getId()) != null)
                            for(Grade g : s.getGrades(cl.getId()))
                            {
                                if(names)
                                {
                                    for (Field f : list.values()) {
                                        String str = f.getName();
                                        String cap = "get" + str.substring(0, 1).toUpperCase() + str.substring(1);
                                        Method m = g.getClass().getDeclaredMethod(cap);

                                        pw.print(m.invoke(g) + ";" );
                                    }
                                    pw.println();
                                }else
                                    pw.println(g.getId() + ";" + g.getValue() + ";" + g.getId_subject() + ";" + g.getId_student() + ";");
                            }
                    }
            }

            pw.flush();
            pw.close();
    }

    public boolean importCsv()
    {
        try {
            ClassContainer gr = new ClassContainer();
            csvImportClass(gr);
            csvImportStudent(gr);
            csvImportGrade(gr);

            group = gr;

            refreshAllSubject();
            refreshStudent();
            refreshSubject();
            refreshGrades();
        }catch(MyException e)
        {
            return false;
        }

        return true;
    }

    public void csvImportGrade(ClassContainer gr) throws MyException
    {
        int[] r = new int[4];
        String[] names = {"id", "value", "id_subject", "id_student"};
        String line = "";
        int ids = 0;
        BufferedReader reader = null;


        try{
            reader = new BufferedReader(new FileReader("Grade.csv"));
            for(boolean i=true; (line = reader.readLine()) != null; i=false){
                String[] row = line.split(";");

                if(i)
                {
                    if(row[0].length() > 1) {
                        int who = 0;
                        int which;
                        for (String in : names) {
                            which = 0;
                            for (String s : row) {

                                if (s.equals(in)) {
                                    r[who] = which;
                                }
                                which++;
                            }
                            who++;
                        }
                    }else
                    {
                        for( int k = 0; k<4; k++)
                            r[k] = k;
                    }
                }else {

                    if (ids < Integer.parseInt(row[r[0]]))
                        ids = Integer.parseInt(row[r[0]]);

                    Grade g = new Grade(Integer.parseInt(row[r[1]]), Integer.parseInt(row[r[2]]), Integer.parseInt(row[r[3]]));

                    boolean done = false;
                    for (Class cl : gr.getM().values()) {
                        for (Student s : cl.getStudentlist()) {
                            if (s.getId() == Integer.parseInt(row[r[3]])) {
                                s.addGradeAll(Integer.parseInt(row[r[2]]), Integer.parseInt(row[r[1]]), Integer.parseInt(row[r[0]]));
                                done = true;
                                break;
                            }
                        }
                        if (done)
                            break;
                    }
                }

            }
            Grade.setIds(ids);
        }catch(Exception e) {

            dialogBoxWarning("Error, import grade failed!");
            throw new MyException("import grade failed");
        } finally{
            try {
                if(reader!=null)
                    reader.close();
            } catch(IOException e){
                dialogBoxWarning("Error, import grade can't be closed!");
            }
        }
    }

    public void csvImportStudent(ClassContainer gr) throws MyException
    {
        int[] r = new int[5];
        String[] names = {"id", "name", "sname", "year", "id_subject"};
        String line = "";
        int ids = 0;
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader("Student.csv"));
            for(boolean i=true; (line = reader.readLine()) != null; i=false){
                String[] row = line.split(";");

                if(i)
                {
                    if(row[0].length() > 1) {
                        int who = 0;
                        int which;
                        for (String in : names) {
                            which = 0;
                            for (String s : row) {

                                if (s.equals(in)) {
                                    r[who] = which;
                                }
                                which++;
                            }
                            who++;
                        }
                    }else
                    {
                        for( int k = 0; k<5; k++)
                            r[k] = k;
                    }
                }else
                {

                    if (ids < Integer.parseInt(row[r[0]]))
                        ids = Integer.parseInt(row[r[0]]);

                    Student s = new Student(row[r[1]], row[r[2]], Integer.parseInt(row[r[3]]));
                    s.setId(Integer.parseInt(row[r[0]]));

                    for (Class cl : gr.getM().values()) {
                        if (cl.getId() == Integer.parseInt(row[r[4]])) {
                            s.setStudent_con(StudentCondition.OCZEKIWANY, cl.getId());
                            cl.addStudent(s);
                            break;
                        }
                    }
                }
            }
            Student.setIds(ids);
        }catch(Exception e) {
            dialogBoxWarning("Error, import student failed!");
            throw new MyException("import student failed");
        } finally{
            try {
                if(reader!=null)
                    reader.close();
            } catch(IOException e){
                dialogBoxWarning("Error, import subject can't be closed!");
            }
        }
    }

    public void dialogBoxWarning(String s)
    {
        Alert dg = new Alert(Alert.AlertType.WARNING);
        dg.setTitle("Error!");
        dg.setContentText(s);
        dg.show();
    }

    public void csvImportClass(ClassContainer gr) throws MyException
    {
        int[] r = new int[3];
        String[] names = {"id", "group_name", "max_student"};
        String line = "";
        int ids = 0;
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader("Class.csv"));
            for(boolean i=true; (line = reader.readLine()) != null; i=false){
                String[] row = line.split(";");

                if(i)
                {
                    if(row[0].length() > 1) {
                        int who = 0;
                        int which;
                        for (String in : names) {
                            which = 0;
                            for (String s : row) {

                                if (s.equals(in)) {
                                    r[who] = which;
                                }
                                which++;
                            }
                            who++;
                        }
                    }else
                    {
                        for( int k = 0; k<3; k++)
                            r[k] = k;
                    }
                }else {
                    if (ids < Integer.parseInt(row[r[0]]))
                        ids = Integer.parseInt(row[r[0]]);

                    Class cl = new Class(row[r[1]], Integer.parseInt(row[r[2]]));
                    cl.setId(Integer.parseInt(row[r[0]]));
                    gr.setClass(cl);
                }
            }
            Class.setIds(ids);
        }catch(Exception e) {
            dialogBoxWarning("Error, import subject failed!");
            throw new MyException("import class failed");
        } finally{
            try {
                if(reader!=null)
                    reader.close();
            } catch(IOException e){
                dialogBoxWarning("Error, import subject can't be closed!");
            }
        }


    }

    public void serialization()
    {
        try {

            // Saving of object in a file
            FileOutputStream file = new FileOutputStream(fieldOur.getText());
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(group);

            out.close();
            file.close();
            //System.out.println(group.getM().get(0).getId());

        } catch (IOException ex) {
            dialogBoxWarning("Error, serialization failed!");
        }
    }

    public void deserialization()
    {
        try {

            // Reading the object from a file
            FileInputStream file = new FileInputStream(fieldOur.getText());
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            group = (ClassContainer) in.readObject();

            in.close();
            file.close();

            int Class_id=0;
            int Student_id=0;
            int Grade_id=0;

            if(group.getM().values() != null)
                for (Class cl : group.getM().values())
                {
                    if(Class_id<cl.getId())
                        Class_id = cl.getId();
                    if(cl.getStudentlist() != null)
                        for(Student s : cl.getStudentlist())
                        {
                            if(Student_id<s.getId())
                                Student_id = s.getId();
                            s.setStudent_con(StudentCondition.OCZEKIWANY, cl.getId());

                            if(s.getGrades(cl.getId()) != null)
                                for(Grade g : s.getGrades(cl.getId()))
                                {
                                    if(Grade_id < g.getId())
                                        Grade_id = g.getId();
                                }
                        }
                }

            Class.setIds(Class_id);
            Student.setIds(Student_id);
            Grade.setIds(Grade_id);



            refreshStudent();
            refreshSubject();
            refreshGrades();
            refreshAllSubject();



        } catch (IOException ex) {
            dialogBoxWarning("Error, deserialization failed!");
        } catch (ClassNotFoundException ex) {
            dialogBoxWarning("Error, specyfied class not found!");
        }
    }

    public String toolTipSubject(int subject_id)
    {
        String text = "Condition=" + student_global.getStudent_con(subject_id).toString() +
                ", Avg. grade=" + String.valueOf(student_global.getPoints(subject_id) +
                ", Grades=");

        if(student_global.getGrades(subject_id) == null) {
            text += "0.";
            return text;
        }
        List<Grade> grades = student_global.getGrades(subject_id);
        for(int i=0; i<grades.size(); i++) {
            if(i+1==grades.size())
                text += grades.get(i).getValue()+".";
            else
                text += grades.get(i).getValue()+", ";
        }

        return text;
    }

    public class TooltipTableRow<T> extends TableRow<T> {

        private Function<T, String> toolTipStringFunction;

        public TooltipTableRow(Function<T, String> toolTipStringFunction) {
            this.toolTipStringFunction = toolTipStringFunction;
        }

        @Override
        protected void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);
            if(item == null) {
                setTooltip(null);
            } else {
                Tooltip tooltip = new Tooltip(toolTipStringFunction.apply(item));
                setTooltip(tooltip);
            }
        }
    }

    public void removeFromSubject()
    {
        if(subject_global==null)
            return;

        try {
            subject_global.remove(student_global);
        }catch(MyException e){
            return;
        }

        refreshSubject();
        refreshGrades();
        showSubjects();
    }

    public void signUpStudent()
    {
        if(subject_add_global == null || student_global == null)
            return;
        try {
            subject_add_global.addStudent(student_global);
        }catch(MyExceptionArth e){
            dialogBoxWarning("Error, this subject is full");
            return;
        }
        refreshSubject();
        refreshGrades();
        showSubjects();
        student_global.setStudent_con(StudentCondition.ZAPISANY, subject_add_global.getId());
    }

    public void changeCondition()
    {
        if(subject_global == null)
            return;

        StudentCondition cond;

        if(student_global.getStudent_con(subject_global.getId()) == StudentCondition.ODRABIAJACY)
            cond=StudentCondition.CHORY;
        else if(student_global.getStudent_con(subject_global.getId()) == StudentCondition.CHORY)
            cond=StudentCondition.ZDROWY;
        else if (student_global.getStudent_con(subject_global.getId()) == StudentCondition.ZDROWY)
            cond=StudentCondition.NIEOBECNY;
        else
            cond = StudentCondition.ODRABIAJACY;

        student_global.setStudent_con(cond, subject_global.getId());

        global_student_con.setText("Student condition:");
        global_student_con1.setText(student_global.getStudent_con(subject_global.getId()).toString());

    }

    public void deleteGradeEvent()
    {
        if(grade_global==null)
            return;
        student_global.removeGrade(grade_global);
        refreshGrades();
        showGrades();
        global_student_points1.setText(String.valueOf(Math.round(student_global.getAllPoints() * 10.0) / 10.0));
        averageGradeSubject.setText("Average grade for this subject:");
        averageGradeSubject1.setText(String.valueOf(student_global.getPoints(subject_global.getId())));
        tableSubject.getItems().clear();
        Map<Integer, Class> subjects = new HashMap<>();

        for(Class cl : group.getM().values())
        {
            for(Student s : cl.getStudentlist())
            {
                if(s.equals(student_global))
                    subjects.put(cl.getId(), cl);
            }
        }

        if(subjects.size()==0)
            return;

        Subject[] sub = new Subject[subjects.size()];

        int w=0;
        for(Class cl : subjects.values())
        {
            sub[w] = new Subject(cl, student_global.getPoints(cl.getId()));
            tableSubject.getItems().add(sub[w]);
            w++;
        }
    }

    public void addGradeEvent()
    {
        String text = fieldOur.getText();
        String count = "";

        int i=0;
        for(;i<text.length(); i++)
        {
            if(text.charAt(i)==' ')
                break;

            count += text.charAt(i);
        }

        int c;

        try {
            c = Integer.parseInt(count);
        }catch(NumberFormatException w) {
            c=0;
        };

        if(subject_global==null)
            return;

        student_global.addGrade(subject_global.getId(), c);
        refreshGrades();
        showGrades();
        global_student_points1.setText(String.valueOf(Math.round(student_global.getAllPoints() * 10.0) / 10.0));
        averageGradeSubject.setText("Average grade for this subject:");
        averageGradeSubject1.setText(String.valueOf(student_global.getPoints(subject_global.getId())));
        tableSubject.getItems().clear();
        Map<Integer, Class> subjects = new HashMap<>();

        for(Class cl : group.getM().values())
        {
            for(Student s : cl.getStudentlist())
            {
                if(s.equals(student_global))
                    subjects.put(cl.getId(), cl);
            }
        }

        if(subjects.size()==0)
            return;

        Subject[] sub = new Subject[subjects.size()];

        int w=0;
        for(Class cl : subjects.values())
        {
            sub[w] = new Subject(cl, student_global.getPoints(cl.getId()));
            tableSubject.getItems().add(sub[w]);
            w++;
        }

    }

    public void changeSubjectInfo()
    {
        String text = fieldOur.getText();

        String name = "";

        int i;
        for(i=0; i<text.length(); i++)
        {
            if(text.charAt(i)==' ')
                break;

            name += text.charAt(i);
        }

        String count = "";

        i++;
        for(;i<text.length(); i++)
        {
            if(text.charAt(i)==' ')
                break;

            count += text.charAt(i);
        }

        int c;

        if(name.equals(""))
            name="null";

        try {
            c = Integer.parseInt(count);
        }catch(NumberFormatException w) {
            c=0;
        };

        if(subject_add_global==null)
            return;
        if(c<subject_add_global.getStudentlist().size())
            c = subject_add_global.getStudentlist().size();
        group.removeClass(subject_add_global.getGroup_name());
        for(int j=0; j==0;)
        {
            j++;
            for(Class cl: group.getM().values())
            {
                if(name.equals(cl.getGroup_name())) {
                    name += "*";
                    j--;
                    break;
                }
            }
        }
        subject_add_global.setMax_student(c);
        subject_add_global.setGroup_name(name);
        group.setClass(subject_add_global);
        refreshAllSubject();
        showSubjects();
        refreshGrades();
        global_student_con.setText("");
        global_student_con1.setText("");

    }

    public void deleteSubject()
    {
        if(subject_add_global == null)
            return;

        group.removeClass(subject_add_global.getGroup_name());
        refreshAllSubject();
        showSubjects();
        refreshGrades();
        global_student_con.setText("");
        global_student_con1.setText("");
    }

    public void refreshAllSubject()
    {
        allSubjects.getItems().clear();
        createChoiceBoxSubjects();
    }

    public void addSubjectEvent()
    {
        String text = fieldOur.getText();

        String name = "";

        int i;
        for(i=0; i<text.length(); i++)
        {
            if(text.charAt(i)==' ')
                break;

            name += text.charAt(i);
        }

        String count = "";

        i++;
        for(;i<text.length(); i++)
        {
            if(text.charAt(i)==' ')
                break;

            count += text.charAt(i);
        }

        int c;

        if(name.equals(""))
            name="null";

        try {
            c = Integer.parseInt(count);
        }catch(NumberFormatException w) {
            c=0;
        };

        for(int j=0; j==0;)
        {
            j++;
            for(Class cl: group.getM().values())
            {
                if(name.equals(cl.getGroup_name())) {
                    name += "*";
                    j--;
                    break;
                }
            }
        }

        Class cl = new Class(name, c);

        group.setClass(cl);

        refreshAllSubject();
    }

    public void changeStudentInfo()
    {
        String text = fieldOur.getText();

        String name = "";

        int i;
        for(i=0; i<text.length(); i++)
        {
            if(text.charAt(i)==' ')
                break;

            name += text.charAt(i);
        }

        if(name.equals(""))
            name="null";

        String sname = "";

        i++;
        for(;i<text.length(); i++)
        {
            if(text.charAt(i)==' ')
                break;

            sname += text.charAt(i);
        }

        if(sname.equals(""))
            sname="null";

        String year = "";

        i++;
        for(;i<text.length(); i++)
        {
            if(text.charAt(i)==' ')
                break;

            year += text.charAt(i);
        }

        int y;

        try {
            y = Integer.parseInt(year);
        }catch(NumberFormatException w) {
            y=0;
        };
        try {
            if (student_global == null)
                throw new MyException("Nie wybrano studenta.");
            student_global.setName(name);
            student_global.setSname(sname);
            student_global.setYear(y);
            refreshStudent();
            refreshSubject();
            refreshGrades();
        }catch(MyException x){
            dialogBoxWarning("Error, student not choosen!");
        }
    }

    public void refreshStudent()
    {
        tableStudent.getItems().clear();
        grade_global=null;
        fillListStudent();
        clearStudentInfo();

    }

    public void deleteStudentEvent()
    {
        for (Class cl : group.getM().values())
            try {
                cl.remove(student_global);

                tableStudent.getItems().remove(student_global);
                refreshSubject();
                refreshGrades();
                clearStudentInfo();
            }catch(MyException e){
            }
    }

    public void clearStudentInfo()
    {
        global_student_name.setText("");
        global_student_surname.setText("");
        global_student_con.setText("");
        global_student_con1.setText("");
        global_student_year.setText("");
        global_student_year1.setText("");
        global_student_points.setText("");
        global_student_points1.setText("");
    }

    public void addStudentEvent()
    {
        String text = fieldOur.getText();

        String name = "";

        int i;
        for(i=0; i<text.length(); i++)
        {
            if(text.charAt(i)==' ')
                break;

            name += text.charAt(i);
        }

        if(name.equals(""))
            name="null";

        String sname = "";

        i++;
        for(;i<text.length(); i++)
        {
            if(text.charAt(i)==' ')
                break;

            sname += text.charAt(i);
        }

        if(sname.equals(""))
            sname="null";

        String year = "";

        i++;
        for(;i<text.length(); i++)
        {
            if(text.charAt(i)==' ')
                break;

            year += text.charAt(i);
        }

        int y;

        try {
            y = Integer.parseInt(year);
        }catch(NumberFormatException w) {
            y=0;
        };

        Student s = new Student(name, sname, y);

        if(subject_add_global!=null)
            subject_add_global.addStudent(s);

        tableStudent.getItems().add(s);
    }

    public void createChoiceBoxSubjects()
    {
        for(Class cl : group.getM().values())
        {
            allSubjects.getItems().add(cl);
        }
    }

    public void selectGrade()
    {
        grade_global=tableGrades.getSelectionModel().getSelectedItem();
    }

    public void refreshGrades()
    {
        tableGrades.getItems().clear();
        grade_global=null;
        averageGradeSubject.setText("");
        averageGradeSubject1.setText("");
    }

    public void refreshSubject()
    {
        tableSubject.getItems().clear();
        subject_global=null;
    }

    public void createTableGrades()
    {
        grades.setCellValueFactory(new PropertyValueFactory<Grade, Integer>("value"));

        tableStudent.setItems(listStudent);
    }

    public void selectSubject()
    {
        if(tableSubject.getSelectionModel().getSelectedItem() == null)
            return;

        subject_global=tableSubject.getSelectionModel().getSelectedItem().getCl();
        if(subject_global==null)
            return;
        showGrades();
        global_student_con.setText("Student condition:");
        global_student_con1.setText(student_global.getStudent_con(subject_global.getId()).toString());
        averageGradeSubject.setText("Average grade for this subject:");
        averageGradeSubject1.setText(String.valueOf(student_global.getPoints(subject_global.getId())));
    }

    public void showGrades()
    {
        refreshGrades();
        if(student_global.getGrades(subject_global.getId()) == null)
            return;
        List<Grade> grades = student_global.getGrades(subject_global.getId());
        for(int i=0; i<grades.size(); i++) {
            tableGrades.getItems().add(grades.get(i));
        }
    }


    public void fillListStudent()
    {
         Map<Integer, Student> students = new HashMap<>();

         for(Class cl : group.getM().values())
         {
             for(Student s : cl.getStudentlist())
             {
                 students.put(s.getId(), s);
             }
         }

         for(Student s : students.values())
             tableStudent.getItems().add(s);
    }

    public void selectStudent()
    {
        if(tableStudent.getSelectionModel().getSelectedItem() == null)
            return;

        student_global=tableStudent.getSelectionModel().getSelectedItem();

        showSubjects();

        global_student_name.setText(student_global.getName());
        global_student_surname.setText(student_global.getSname());


        global_student_con.setText("");
        global_student_con1.setText("");
        averageGradeSubject.setText("");
        averageGradeSubject1.setText("");

        global_student_year.setText("Year of birth:");
        global_student_year1.setText(String.valueOf(student_global.getYear())+"r.");
        global_student_points.setText("Average grade for all subjects:");
        global_student_points1.setText(String.valueOf(Math.round(student_global.getAllPoints() * 10.0) / 10.0));


    }

    public void showSubjects(){
        Map<Integer, Class> subjects = new HashMap<>();

        for(Class cl : group.getM().values())
        {
            for(Student s : cl.getStudentlist())
            {
                if(s.equals(student_global))
                    subjects.put(cl.getId(), cl);
            }
        }

        refreshSubject();
        refreshGrades();

        if(subjects.size()==0)
            return;

        Subject[] sub = new Subject[subjects.size()];

        int i=0;
        for(Class cl : subjects.values())
        {
            sub[i] = new Subject(cl, student_global.getPoints(cl.getId()));
            tableSubject.getItems().add(sub[i]);
            i++;
        }


    }

    public void createTableStudent()
    {
        names.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<Student, String>("sname"));

        tableStudent.setItems(listStudent);

        fillListStudent();
    }

    public void createTableSubject() {
        subject.setCellValueFactory(new PropertyValueFactory<Subject, String>("group_name"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<Subject, Double>("points"));

        tableSubject.setItems(listSubject);
    }
}