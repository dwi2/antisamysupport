AntiSamy plugin for Play framework
==================================

## Version ##
0.1

## Introduction ##
Play framework provides html escaping and operates automaticlly in its template engine. However, if you have the need of customizing policy of filtering user input of your application, or user input in your application would never have the chance to be processed by template engine of Play framework, then you might need some other tools to filter user input to prevent from attack of injecting malicious JavaScript. 
OWASP AntiSamy Project is one of the best projects to do such things. It allows you customizing filtering policy. If you want to use AntiSamy instantly in your Play framework application and you don't feel like to deal with XML policy file or configuration stuff, this plugin is just for the situation. Follow the *Usage* part of this documentation and start to block malicious input from your application right away.

## Requirement ##
* Play framework version 1.2.3

    The latest official version of Play framework is 1.2.3 by the time I started to learn it. So I wrote and tested this plugin against 1.2.3.
    I am not sure if this plugin works on any other versions. If your application is writted in other version than 1.2.3 and you really like to use this plugin, you can download the source and type the command to build for your Play framework version.
    
        play build-module

## Usage ##

1.  Name your policy file as *antisamy.xml* and put it under **conf/** of your application directory.
    It is OK if you don't have your own policy file or you don't know what is policy file right now. This plugin prepares a default policy file for you. Check out [AntiSamy Project](https://www.owasp.org/index.php/Category:OWASP_AntiSamy_Project) later to know more about policy file. And [Google code of AntiSamy](http://code.google.com/p/owaspantisamy/downloads/list) has some example policy files to download.
2.  Edit *conf/dependencies.yml* as following:
   
        require
            - other module... 
            - antisamysupport -> antisamysupport

        repositories:
            - other repositories...
            - antisamysupport:
                type:      local
                artifact:  "/path/to/antisamysupport"
                contains:
                    - antisamysupport -> *

3.  Invoke **AntiSamyPlugin.filter()** in your code to filter dirty input string , you will get a clean output string in return (*the degree of cleanliness depends on how strict your policy file is*)

        import play.modules.antisamysupport.*;
        ...
        String dirty = "Lorem<script>alert('I am a real bad ass!');</script> ipsum";
        String clean = AntiSamyPlugin.filter(dirty); // should return 'Lorem ipsum'


## Future works ##
* To allow multiple policy files in one single application

## Reference ##

> ### AntiSamy project ###
> [https://www.owasp.org/index.php/Category:OWASP_AntiSamy_Project](https://www.owasp.org/index.php/Category:OWASP_AntiSamy_Project)

> ### AntiSamy Google code ###
> [http://code.google.com/p/owaspantisamy/](http://code.google.com/p/owaspantisamy/)

> ### Play framework ###
> [http://www.playframework.org/](http://www.playframework.org/)
 
