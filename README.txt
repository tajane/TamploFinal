BufferedWriter writer = new BufferedWriter(new FileWriter(new File("note.txt")));

String stuffToWrite = info;

writer.write(stuffToWrite);

writer.close();



FileWriter fw = new FileWriter("oceans.txt",true);
        PrintWriter out = new PrintWriter(fw);

        // Append the name of ocean to the file
        out.println("Southern");

        // Close the file.
        out.close();
