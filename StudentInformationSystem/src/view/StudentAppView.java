package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import controller.StudentController;
import model.Course;
import model.Qualification;
import model.Registration;
import model.Student;

public class StudentAppView {

	public static void main(String[] args) {

		StudentController controller = new StudentController();
		Scanner scn = new Scanner(System.in);
		while (true) {
			System.out.println("1. Student\n2. Admin\n3. Exit");
			int userType = scn.nextInt();

			if (userType == 1) {
				String choice = "y";
				do {
					System.out.println(
							"1. Sign Up\n2. Update Phone Number\n3. View all courses\n4. Register for a course\n5. Sign Out");
					int option = scn.nextInt();
					System.out.println("Continue(Y/N)");

					switch (option) {
					case 1:
						System.out.println("Enter name, date of birth(dd/mm/YYYY), phone number, email, address");
						String name = scn.next();
						String dateOfBirth = scn.next();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						LocalDate dob = LocalDate.parse(dateOfBirth, formatter);

						String phoneNumber = scn.next();
						String email = scn.next();
						String address = scn.next();

						System.out.println("1. Master 2. Graduate 3. Intermediate 4. Matric");
						int q = scn.nextInt();

						Qualification qualification = null;

						if (q == 1)
							qualification = Qualification.Master;
						if (q == 2)
							qualification = Qualification.Graduate;
						if (q == 3)
							qualification = Qualification.Intermediate;
						if (q == 4)
							qualification = Qualification.Matric;

						Student student = new Student(name, dob, qualification, phoneNumber, email, address);

						String message = controller.addNewStudent(student);
						System.out.println(message);
						break;
					case 4:
						System.out.println("Enter your rollno and course you want to register for");
						int rollNo = scn.nextInt();
						int courseId = scn.nextInt();
						String regDateString = scn.next();
						DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						LocalDate regDate = LocalDate.parse(regDateString, formatter2);

						Registration registration = new Registration(regDate, courseId, rollNo);

						message = controller.registrationDb(registration);
						System.out.println(message);
						break;
					}
					System.out.println("continue?(y/n)");
					choice = scn.next();
				} while (choice.equals("y") || choice.equals("Y"));
			} else if (userType == 2) {
				System.out.println("1. View All Users\n2. Find by rollNo\n3. Add new course");
				int option = scn.nextInt();

				switch (option) {
				case 1:
					List<Student> students = controller.viewAllStudents();
					Iterator<Student> iterator = students.iterator();
					while (iterator.hasNext()) {
						Student st = iterator.next();
						System.out.println(st.getRollNo() + " " + st.getName() + " " + st.getQualification());
					}
					break;
				case 2:
					System.out.println("Enter roll no: ");
					int rollNo = scn.nextInt();
					Student st = controller.findStudentByRollNo(rollNo);
					if (st != null) {
						System.out.println(st.getRollNo() + " " + st.getName() + " " + st.getQualification());
					} else {
						System.out.println("Not Found!");
					}
				case 3:
					System.out.println("Enter course name, duration, fee: ");
					String courseName = scn.next();
					int duration = scn.nextInt();
					double fee = scn.nextDouble();

					System.out.println("1. Master 2. Graduate 3. Intermediate 4. Matric");
					int q = scn.nextInt();

					Qualification eligibility = null;

					if (q == 1)
						eligibility = Qualification.Master;
					else if (q == 2)
						eligibility = Qualification.Graduate;
					else if (q == 3)
						eligibility = Qualification.Intermediate;
					else if (q == 4)
						eligibility = Qualification.Matric;

					Course course = new Course(courseName, duration, fee, eligibility);
					String message = controller.addNewCourse(course);
					System.out.println(message);
					break;
				}
			} else {
				System.exit(0);
			}
		}
	}
}
