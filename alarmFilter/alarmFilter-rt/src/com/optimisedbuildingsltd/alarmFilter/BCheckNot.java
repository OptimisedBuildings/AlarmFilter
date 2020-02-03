package com.optimisedbuildingsltd.alarmFilter;

import javax.baja.alarm.BAlarmRecord;
import javax.baja.naming.BOrd;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;
import java.util.logging.Logger;

@NiagaraType


public class BCheckNot extends BCheck {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.optimisedbuildingsltd.alarmFilter.BCheckAnd(2979906276)1.0$ @*/
/* Generated Tue Jan 07 15:47:13 GMT 2020 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BCheckNot.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
public Logger logger = Logger.getLogger("ob.alarmFilter");

    public BBoolean doCheck(BAlarmRecord record){
        BComponent[] children = this.getChildComponents();
        for(BComponent child:children){
            try{
                this.setLastCheckValue(!((BBoolean)child.invoke(child.getAction("check"), record)).getBoolean());
                return BBoolean.make(!((BBoolean)child.invoke(child.getAction("check"), record)).getBoolean());
            } catch (Exception e){logger.info(e.toString());}
        }
        this.setLastCheckValue(false);
        return BBoolean.FALSE;
    }

    public BIcon getIcon() {
        BValue dynamic = this.get("icon");
        return dynamic instanceof BIcon ? (BIcon)dynamic : icon;
    }

    private static final BIcon icon = BIcon.make(BOrd.make("module://alarmFilter/res/NOT.Png"));
}
