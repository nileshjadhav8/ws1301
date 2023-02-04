package com.example.ws1301.ws1301.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


import org.springframework.stereotype.Service;

import com.example.ws1301.ws1301.model.Contact;
import com.example.ws1301.ws1301.validations.AgeValidationException;
@Service
public class DataDirServiceImpl implements DataDirService {
   
    private String path = "/users/nileshjadhav/data/";

    @Override
    public Contact saveContact(Contact contact) {

   
       if(!validateAge(contact.getDob())){
            throw new AgeValidationException("Age is not valid.");
       }

        // generate hex number as id and set in contact
        String hexid = generateHexId();

        System.out.println("hex id created:"+hexid);
        contact.setId(hexid);
        // create file of name hex id
        createFile(hexid);
        // save contact in file
        String fullpath = path + hexid + ".txt";

        System.out.println("fullpath : " +fullpath);
        // try{
        FileWriter fw;
        try {
            fw = new FileWriter(fullpath);

            fw.write(contact.getName() + "\n" + contact.getEmail() + "\n" + contact.getPhone() + "\n"
                    + contact.getDob());
                    fw.close();
        } catch (IOException e) {
            
            e.printStackTrace();
        
          
        }
        return contact;
    }


    private boolean validateAge(LocalDate dob){
		int age=0;
        LocalDate curDate = LocalDate.now();  
		
		if ((dob != null) && (curDate != null))   
		{  
		age  = Period.between(dob, curDate).getYears();
		}  

        if(age > 10 && age <100) return true;
        else return false;
    }
    private String generateHexId() {

        Random random = new Random();

        StringBuilder sb = new StringBuilder();

        while (sb.length() < 8) {

            sb.append(Integer.toHexString(random.nextInt()));

            sb.setLength(8);

        }
        return sb.toString();
    }

    private void createFile(String filename) {
        try {
            File file = new File(path + filename + ".txt");

            if (file.createNewFile()) {
                System.out.println("File Created:" + file.getName());
            }else{
                System.out.println("File already created.");
            }


        } catch (IOException e) {

        }
    }


    public Contact searchContactById(String id) {
       

		String path = "/users/nileshjadhav/data";
		File directory = new File(path);
		Contact contact = new Contact();

		String[] flist = directory.list();

		int flag = 0;
//search file
		if (flist == null) {
			System.out.println("Empty directory.");
		} else {

			for (int i = 0; i < flist.length; i++) {
				String filename = flist[i];
				if (filename.equalsIgnoreCase(id + ".txt")) {
					System.out.println(filename + " found");
					flag = 1;
				}
			}
		}
//read file
		if (flag == 0) {
			System.out.println("File Not Found");
		} else {

			File file = new File(path + "/" + id + ".txt");
			BufferedReader br;

			List<String> list = new ArrayList<String>();
			try {
				br = new BufferedReader(new FileReader(file));
				String st;
				while ((st = br.readLine()) != null) {
					System.out.println(st);
					list.add(st);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
//populate Contact object
			if (list.size() > 0) {
				contact.setId(id);
				contact.setName(list.get(0));
				contact.setEmail(list.get(1));
				contact.setPhone(list.get(2));
				contact.setDob(LocalDate.parse(list.get(3).toString()));
			}
		}
		return contact;
	
    }


    public Map<String, Contact> fetchAllcontacts() {
		String path = "/users/nileshjadhav/data";
		File directory = new File(path);
		Contact contact = new Contact();

		Map<String, Contact> userDirectory = new HashMap<String, Contact>();

		String[] flist = directory.list();

		if (flist == null) {
			System.out.println("Empty directory.");
		} else {

			for (int i = 0; i < flist.length; i++) {
				String filename = flist[i];
				//String filePath = path =
				if (filename.endsWith(".txt")) {

					String filepath = path + "/" + filename;
					
					//System.out.println("----->"+filepath);

					File file = new File(filepath);
					BufferedReader br;

					List<String> list = new ArrayList<String>();
					try {
						br = new BufferedReader(new FileReader(file));
						String st;
						while ((st = br.readLine()) != null) {
							//System.out.println(st);
							list.add(st);
						}

					} catch (IOException e) {
						e.printStackTrace();
					}
					
					String id = filename.substring(0, filename.lastIndexOf('.'));

					if (list.size() > 0) {
						contact.setId(id);
						contact.setName(list.get(0));
						contact.setEmail(list.get(1));
						contact.setPhone(list.get(2));
						contact.setDob(LocalDate.parse(list.get(3).toString()));
					}



					String link = filepath.substring(0, filepath.lastIndexOf('.'));


					userDirectory.put(link, contact);

				}
			} // end for
		}

		return userDirectory;
	
    }

}
