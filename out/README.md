# Password Cracker

A simple password cracker tool in Java with support for different techniques.

## Usage

To use the Password Cracker, run the following command:

```bash
java PasswordCracker <password> <technique> <hashType>

<password>: The target password you want to crack.
<technique>: The technique to use for cracking. Use -bf for brute force or -d for dictionary.
<hashType>: The type of hash used for the password. Use -p for plaintext, -m for MD5, -b for BCrypt, and -s for SHA256.
```

## Examples


To crack a password using brute force with plaintext:

```bash
java PasswordCracker mypassword -bf -p
```

To crack a password using a dictionary attack with SHA256 hash:
```bash
java PasswordCracker securepass -d -s
```

## Installation
1. Clone the repository:
```bash
https://github.com/y3llowfellow/password
```
2. Compile the Java code:
```bash
javac Password.java
```
3. Run the program as described in the usage section.