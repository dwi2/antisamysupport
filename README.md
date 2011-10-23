
AntiSamy plugin for Play framework
==================================


## Usage ##

1.  name your policy file as *antisamy.xml* and put it under conf/ of your application directory
2.  edit *conf/dependencies.yml* as following:
   
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

3.  invoke **AntiSamyPlugin.filter()** in your code to filter dirty input string , you will get a clean output string in return (*the degree of cleanliness depends on how strict your policy file is*)

        import play.modules.antisamysupport.*;
        ...
        String dirty = "<script>alert('I am a real bad ass!');</script>";
        String clean = AntiSamyPlugin.filter(dirty);


## Reference ##

> ### AntiSamy project ###
> [https://www.owasp.org/index.php/Category:OWASP_AntiSamy_Project](https://www.owasp.org/index.php/Category:OWASP_AntiSamy_Project)

> ### AntiSamy Google code ###
> [http://code.google.com/p/owaspantisamy/](http://code.google.com/p/owaspantisamy/)

> ### Play framework ###
> [http://www.playframework.org/](http://www.playframework.org/)
 
