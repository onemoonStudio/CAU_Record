package lecture.ch11;

import java.util.Date;

public class Tcl implements Cloneable{
    int hell = 1;
    Date d = new Date();
    public Tcl(){

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Tcl result = (Tcl)super.clone();
        result.d = (Date)(d.clone());
        return result;
    }
}
