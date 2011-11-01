AntiSamy plugin for Play framework
==================================

## Version ##
0.1

## Introduction ##
Play framework provides html escaping and operates automaticlly in its template engine. However, if you have the need of customizing policy of filtering user input of your application, or user input in your application would never have the chance to be processed by template engine of Play framework, then you might need some other tools to filter user input to prevent attack of injecting malicious JavaScript. 
OWASP AntiSamy Project is one of the best projects to do such things. It allows you customizing filtering policy. If you want to use AntiSamy instantly in your Play framework application and you don't feel like to deal with XML policy file or configuration stuff, this plugin is just for the situation. Follow the *Usage* part of this documentation and start to block malicious input from your application right away.

## Requirement ##
* [Play framework version 1.2.3](http://www.playframework.org/)

    The latest official version of Play framework is 1.2.3 by the time I started to learn it. So I wrote and tested this plugin against 1.2.3.
    I am not sure whether this plugin works on any other versions or not. If your application is written in version other than 1.2.3 and you really like to use this plugin, you can download the source and type the command to build for your Play framework version.
    
        $> play deps --sync; play build-module

* [Apache Ant](http://ant.apache.org/)

    We need Ant to build antisamysupport 

## Usage ##

### Use the prebuild version ###
1. Download the [prebuild zip file](https://github.com/dwi2/antisamysupport/raw/master/dist/antisamysupport-0.1.zip) and extract it into a directory called *antisamysupport*. (or any other names you like) 

2. Name your policy file as *antisamy.xml* and put it under **conf/** of your application directory.
    It is fine if you don't have your own policy file or you don't know what is policy file right now. This plugin prepares a default policy file for you. Check out [AntiSamy Project](https://www.owasp.org/index.php/Category:OWASP_AntiSamy_Project) later to know more about policy file. And [Google code of AntiSamy](http://code.google.com/p/owaspantisamy/downloads/list) has some example policy files to download.
3. Edit *conf/dependencies.yml* of your application as follows:
   
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

4. Invoke **AntiSamyPlugin.filter()** in your code to filter dirty input string , you will get a clean output string in return (*the degree of cleanliness depends on how strict your policy file is*)

        import play.modules.antisamysupport.*;
        ...
        String dirty = "Lorem<script>alert('I am a real bad ass!');</script> ipsum";
        String clean = AntiSamyPlugin.filter(dirty); // should return 'Lorem ipsum'

### Build from source ###
1. Download [source code](https://github.com/dwi2/antisamysupport) of antisamy plugin

        $> git clone git://github.com/dwi2/antisamysupport.git 

2. Resolve dependencies and build antisamysupport

        $antisamysupport> play deps --sync; play build-module 

3. Follow the step 2 to step 4 in the above section of **Use the prebuild version**

## Future works ##
* To allow multiple policy files in one single application
* Support two scanning mode of antisamy: dom scan and sax scan
* Contribute to Play Framework

## Reference ##

**AntiSamy project:**
[https://www.owasp.org/index.php/Category:OWASP_AntiSamy_Project](https://www.owasp.org/index.php/Category:OWASP_AntiSamy_Project)

**AntiSamy Google code:**
[http://code.google.com/p/owaspantisamy/](http://code.google.com/p/owaspantisamy/)

**Play framework:**
[http://www.playframework.org/](http://www.playframework.org/)
 
