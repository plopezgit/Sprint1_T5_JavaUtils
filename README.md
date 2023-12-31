# Sprint1_T5_JavaUtils


```bash
java -version
```

Level 1

Java can be used exclusively, or the Apache Commons IO library if you prefer.

- Exercise 1

Create a class that alphabetically lists the contents of a directory received by parameter.

```bash
javac src/n1Exe1/App.java
java -cp src n1Exe1.App
```

- Exercise 2

Add to the class from the previous exercise, the functionality to list a directory tree with the contents of all its levels (recursively) so that they are printed to the screen in alphabetical order within each level, also indicating whether it is a directory (D) or a file (F), and its last modified date.

```bash
javac src/n1Exe2/App.java
java -cp src n1Exe2.App
```


- Exercise 3

Modify the previous exercise. Now, instead of displaying the result on the screen, it saves the result in a TXT file.

```bash
javac src/n1Exe3/App.java
java -cp src n1Exe3.App
```

- Exercise 4

Adds the functionality to read any TXT file and display its contents by console.

```bash
javac src/n1Exe4/App.java
java -cp src n1Exe4.App
```

- Exercise 5

Now the program needs to serialize a Java Object to a .ser file and then deserialize it.

```bash
javac src/n1Exe5/App.java
java -cp src n1Exe5.App
```


Level 2

- Exercise 1

Run exercise 3 from the previous level by parameterizing all methods in a configuration file.

You can use a Java Properties file, or the Apache Commons Configuration library if you prefer.

From the previous exercise, parameterize the following:

Directory to read.

Name and directory of the resulting TXT file.

```bash
javac src/n2Exe1/App.java
java -cp src n2Exe1.App
```
	
Level 3

- Exercise 1

Create a utility that encrypts and decrypts the files resulting from the previous levels.

Use AES algorithm in ECB or CBC working mode with PKCS5Padding padding method. Either javax.crypto or org.apache.commons.crypto can be used.

```bash
javac src/n3Exe1/App.java
java -cp src n3Exe1.App
```
