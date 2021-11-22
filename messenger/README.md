# tdd
study tdd

TDD by each commit

## To run in CONSOLE mode
`java -jar messenger.jar`

with parameters
```
address=hello@abc.com
user=Tuan
moduleName=TDD
```

Sample
```
java -jar .\build\libs\messenger.jar
Enter parameters for template in format parameterName=parameterValue
end entering parameters with new line
address=hello@abc.com
user=Tuan
moduleName=TDD

Client address: johndoe@myapp.com
To: hello@abc.com
Hello Tuan,
Today we are studying TDD.
This message is generated based on the CONSOLE template.

```

## To run in FILE mode
```
--template-file template-study.txt --params-file params.txt --output-file output-file.txt
```
For now can run on IDE but cannot run on jar file.