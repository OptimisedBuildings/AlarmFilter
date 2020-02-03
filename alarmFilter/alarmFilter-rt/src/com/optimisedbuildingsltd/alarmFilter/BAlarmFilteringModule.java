package com.optimisedbuildingsltd.alarmFilter;

import javax.baja.alarm.BAlarmClassFolder;
import javax.baja.alarm.BAlarmRecord;
import javax.baja.naming.BOrd;
import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraTopic;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;
import java.util.logging.Logger;

@NiagaraType
@NiagaraAction(
        name = "routeAlarm",
        parameterType = "BAlarmRecord",
        defaultValue = "new BAlarmRecord()"
)

public class BAlarmFilteringModule extends BComponent {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.optimisedbuildingsltd.alarmFilter.BAlarmFilteringModule(2017690268)1.0$ @*/
/* Generated Tue Jan 07 16:13:50 GMT 2020 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Action "routeAlarm"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code routeAlarm} action.
   * @see #routeAlarm(BAlarmRecord parameter)
   */
  public static final Action routeAlarm = newAction(0, new BAlarmRecord(), null);
  
  /**
   * Invoke the {@code routeAlarm} action.
   * @see #routeAlarm
   */
  public void routeAlarm(BAlarmRecord parameter) { invoke(routeAlarm, parameter, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BAlarmFilteringModule.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    public Logger logger = Logger.getLogger("ob.alarmFilter");

    public void doRouteAlarm(BAlarmRecord record){
        logger.info("alarm received");
        BComponent[] children = this.getChildComponents();
        for(BComponent child : children){
            try{
                ((BAlarmFilter)child).doRouteAlarm(record);
            } catch (Exception e){
                System.out.println(e.toString());
            }
        }

    }

    public BIcon getIcon() {
        BValue dynamic = this.get("icon");
        return dynamic instanceof BIcon ? (BIcon)dynamic : icon;
    }

    private static final BIcon icon = BIcon.make(BOrd.make("module://alarmFilter/res/AFM.Png"));

}
