package com.optimisedbuildingsltd.alarmFilter;

import javax.baja.alarm.BAlarmRecord;
import javax.baja.naming.BOrd;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;
import java.util.logging.Logger;

@NiagaraType


public class BCheckOr extends BCheck {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.optimisedbuildingsltd.alarmFilter.BCheckAnd(2979906276)1.0$ @*/
/* Generated Tue Jan 07 15:47:13 GMT 2020 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BCheckOr.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
    private Logger logger = Logger.getLogger("ob.alarmFilter");

    public BBoolean doCheck(BAlarmRecord record){
        //Returns the Or'ed value of the child checks. For speed, returns immediately as soon as a TRUE value is found.
        //Returns false if no checks found.
        boolean check = false;
        BComponent[] children = this.getChildComponents();
        for(BComponent child:children){
            try{
                check = check || ((BBoolean)child.invoke(child.getAction("check"), record)).getBoolean();
            } catch (Exception e){logger.info(e.toString());}
        }
        this.setLastCheckValue(check);
        return BBoolean.make(check);
    }

    public BIcon getIcon() {
        BValue dynamic = this.get("icon");
        return dynamic instanceof BIcon ? (BIcon)dynamic : icon;
    }

    private static final BIcon icon = BIcon.make(BOrd.make("module://alarmFilter/res/OR.Png"));
}
