import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("serial")
class Employee implements Serializable{

	int id;
	String name;
	float salary;
	long contact_no;
	String email_id;
	
	public Employee(int id, String name, float salary, long contact_no, String email_id)
	{
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.contact_no = contact_no;
		this.email_id = email_id;
	}
	
	public String toString()
	{
		return "\nСведения о сотруднике: " + "\nID: " + this.id + "\nИмя: " + this.name + "\nЗарплата: " + 
				this.salary + "\nКонтактный номер: " + this.contact_no + "\nАдрес электронной почты: " + this.email_id;
	}
}

class EmployeeManagement
{	
	static void display(ArrayList<Employee> al)
	{
		System.out.println("\n--------------Список сотрудников---------------\n");
		System.out.println(String.format("%-10s%-15s%-10s%-20s%-10s", "ID","Имя","Зарплата","Контактный номер","Адрес электронной почты"));
		for(Employee e : al)
		{
			System.out.println(String.format("%-5s%-20s%-10s%-15s%-10s",e.id,e.name,e.salary,e.contact_no,e.email_id));
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{
		int id;
		String name;
		float salary;
		long contact_no;
		String email_id;
		
		Scanner sc = new Scanner(System.in);
		ArrayList<Employee> al = new ArrayList<Employee>();
		
		File f = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		FileOutputStream fos = null;
		ObjectOutputStream oos =null;
		try{
			f = new File("C:/Users/Юра Ветров/EmployeeDataList1.txt");
			if(f.exists())
			{
				fis = new FileInputStream(f);
				ois = new ObjectInputStream(fis);
				al = (ArrayList<Employee>)ois.readObject();
			}
		}
		catch(Exception exp){
			System.out.println(exp);
		}
		do
		{
			System.out.println("\n*********Добро пожаловать в систему управления сотрудниками**********\n");
			System.out.println("1). Добавить сотрудника в базу данных\n" +
								"2). Поиск сотрудника\n" +
								"3). Редактировать сведения о сотруднике\n" +
								"4). Удалить сведения о сотруднике\n" +
								"5). Показать всех Сотрудников, работающих в этой компании\n" +
								"6). ВЫХОД\n");
			System.out.println("Введите свой выбор : ");
			int ch = sc.nextInt();
			
			switch(ch)
			{
			case 1:System.out.println("\nВведите следующие данные в список ДОБАВИТЬ:\n");
				System.out.println("Введите ID :");
				id = sc.nextInt();
				System.out.println("Введите имя :");
				name = sc.next();
				System.out.println("Введите зарплату :");
				salary = sc.nextFloat();
				System.out.println("Введите номер контакта :");
				contact_no = sc.nextLong();
				System.out.println("Введите адрес электронной почты :");
				email_id = sc.next();
				al.add(new Employee(id, name, salary, contact_no, email_id));
				display(al);
				break;
				
			case 2: System.out.println("Введите правильный вариант из списка :");
					id = sc.nextInt();
					int i=0;
					for(Employee e: al)
					{
						if(id == e.id)
						{
							System.out.println(e+"\n");
							i++;
						}
					}
					if(i == 0)
					{
						System.out.println("\nИнформация о сотруднике недоступна, введите действительный ID!!");
					}
					break;
			
			case 3: System.out.println("\nВведите идентификатор сотрудника, чтобы ИЗМЕНИТЬ детали");
					id = sc.nextInt();
					int j=0;
					for(Employee e: al)
					{
						if(id == e.id)
						{	
							j++;
						do{
							int ch1 =0;
							System.out.println("\nРЕДАКТИРОВАТЬ сведения о сотруднике :\n" +
												"1). Идентификатор сотрудника ID\n" +
												"2). Имя\n" +
												"3). Зарплата\n" +
												"4). Контактный номер\n" +
												"5). Адрес электронной почты\n" +
												"6). ВЕРНУТЬСЯ\n");
							System.out.println("Введите свой выбор : ");
							ch1 = sc.nextInt();
							switch(ch1)
							{
							case 1: System.out.println("\nВведите новый идентификатор сотрудника ID :");
									e.id =sc.nextInt();
									System.out.println(e+"\n");
									break;
							
							case 2: System.out.println("Введите имя нового сотрудника :");
									e.name =sc.nextLine();
									System.out.println(e+"\n");
									break;
									
							case 3: System.out.println("Введите новую зарплату сотрудника :");
									e.salary =sc.nextFloat();
									System.out.println(e+"\n");
									break;
									
							case 4: System.out.println("Введите новый контактный номер сотрудника :");
									e.contact_no =sc.nextLong();
									System.out.println(e+"\n");
									break;
									
							case 5: System.out.println("Введите новый адрес электронной почты сотрудника :");
									e.email_id =sc.next();
									System.out.println(e+"\n");
									break;
									
							case 6: j++;
									break;
									
							default : System.out.println("\nВведите правильный вариант из списка :");
										break;
							}
							}
						while(j==1);
						}
					}
					if(j == 0)
					{
						System.out.println("\nИнформация о сотруднике недоступна, введите действительный ID!!");
					}
				
					break;
					
			case 4: System.out.println("\nВведите идентификатор сотрудника для УДАЛЕНИЯ из базы данных :");
					id = sc.nextInt();
					int k=0;
					try{
					for(Employee e: al)
					{
						if(id == e.id)
						{
								al.remove(e);
								display(al);
								k++;
						}
					}
					if(k == 0)
					{
						System.out.println("\nИнформация о сотруднике недоступна, введите действительный ID!!");
					}
					}
					catch(Exception ex){
						System.out.println(ex);
					}
					break;
			
			case 5: try {
					al = (ArrayList<Employee>)ois.readObject();
				
				} catch (ClassNotFoundException e2) {
					
					System.out.println(e2);
				} catch (Exception e2) {
					
					System.out.println(e2);
				}
					display(al);
					break;
			
			case 6: try {
					fos = new FileOutputStream(f);
					oos = new ObjectOutputStream(fos);
					oos.writeObject(al);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				catch(Exception e2){
					e2.printStackTrace();
				}
				finally{
					try {
						fis.close();
						ois.close();
						fos.close();
						oos.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
					System.out.println("\nВы выбрали ВЫХОД!! Сохранение файлов и закрытие инструмента.");
					sc.close();
					System.exit(0);
					break;
					
			default : System.out.println("\nВведите правильный вариант из списка :");
						break;
			}
		}
		while(true);
	}
}
