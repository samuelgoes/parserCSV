# PARSER CSV

## Installation

```
cd application_folder
mvn clean install
```

## Execution

```
cd application_folder
java -jar target/parserCSV-1.0-SNAPSHOT.jar hackerFile hacsv
```


## Algorithm

As decimal (0123456789) and binary (01) systems, if there is a new system and we know its base (54?t), we can apply
this algorithm to convert into decimal system.

Using the order in the base, 5 --> 0, 4 --> 1, ? --> 2 and t --> 3, so in this example (?4?)
2 (?) * 4 (base size)⁰ where 0 is the position in the number beginning for the last position, so:
2 * 4⁰ + 1 * 4¹ + 2 * 4² = 2 + 4 + 32 = 38

```
        byte[] sco = scoreHack.getBytes();
        int score = 0;

        for(int i = 0 ; i < sco.length ; i++) {
            score += system.indexOf(sco[sco.length - 1 - i]) * Math.pow(system.length(), i);
        }

        return score;
```

## Technology
* Java 8
* JUnit
* Maven