package com.optimisedbuildingsltd.alarmFilter;

import javax.baja.alarm.*;
import javax.baja.naming.BOrd;
import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraTopic;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;
import javax.baja.timezone.BTimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.baja.alarm.BAlarmClass.ESCALATED;
import static javax.baja.alarm.BAlarmClass.priority;

@NiagaraType

@NiagaraAction(
        name = "routeAlarm",
        parameterType = "BAlarmRecord",
        defaultValue = "new BAlarmRecord()"
)
@NiagaraTopic(
        name = "alarm",
        eventType = "BAlarmRecord"
)
@NiagaraProperty(
        name = "priority",
        type = "BInteger",
        defaultValue = "BInteger.make(-1)"
)

public class BAlarmFilter extends BComponent {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.optimisedbuildingsltd.alarmFilter.BAlarmFilter(2146565497)1.0$ @*/
/* Generated Wed Jan 08 09:55:30 GMT 2020 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "priority"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code priority} property.
   * @see #getPriority
   * @see #setPriority
   */
  public static final Property priority = newProperty(0, ((BInteger)(BInteger.make(-1))).getInt(), null);
  
  /**
   * Get the {@code priority} property.
   * @see #priority
   */
  public int getPriority() { return getInt(priority); }
  
  /**
   * Set the {@code priority} property.
   * @see #priority
   */
  public void setPriority(int v) { setInt(priority, v, null); }

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
// Topic "alarm"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code alarm} topic.
   * @see #fireAlarm
   */
  public static final Topic alarm = newTopic(0, null);
  
  /**
   * Fire an event for the {@code alarm} topic.
   * @see #alarm
   */
  public void fireAlarm(BAlarmRecord event) { fire(alarm, event, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BAlarmFilter.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    public Logger logger = Logger.getLogger("ob.alarmFilter");

    public void doRouteAlarm(BAlarmRecord alarmRecord){
        boolean check = true;
        logger.info("Running check for " + this.getDisplayName(null));
        BComponent[] children = this.getChildComponents();
        for(BComponent child:children){
            try{
                logger.info("checking " + child.getDisplayName(null) + ". Current value is " + check);
                check = check && ((BCheck)child).doCheck(alarmRecord).getBoolean();
                logger.info("returned " + ((BCheck)child).doCheck(alarmRecord).getBoolean());
            } catch (Exception e){logger.severe("Failure : " + e.toString());}
        }
        logger.info("final result " + check);
        if(!check){return;}

        //BAlarmClass

        //alarmRecord.setPriority(this.getPriority());

            if (!this.isRunning()) {
                return;
            }

            BSourceState newState = alarmRecord.getSourceState();
            alarmRecord.setPriority(this.getPriority());
            alarmRecord.setAlarmClass(this.getName());
            alarmRecord.setAlarmData(BFacets.make(alarmRecord.getAlarmData(), "TimeZone", BTimeZone.getLocal()));

            if (alarmRecord.getSourceState() == BSourceState.alert && alarmRecord.getAckState() == BAckState.acked) {
                alarmRecord.setSourceState(BSourceState.normal);
                alarmRecord.setAlarmTransition(BSourceState.alert);
            }

            if (alarmRecord.getAlarmData().get(ESCALATED) == null) {
                alarmRecord.addAlarmFacet(ESCALATED, BString.make(""));
            }

            alarmRecord.setLastUpdate(BAbsTime.now());
            BAlarmService as = (BAlarmService)Sys.getService(BAlarmService.TYPE);

            try {
                AlarmDbConnection conn = as.getAlarmDb().getDbConnection((Context)null);
                Throwable var6 = null;

                try {
                    if (conn.getRecord(alarmRecord.getUuid()) == null) {
                        conn.append(alarmRecord);
                    } else {
                        conn.update(alarmRecord);
                    }
                } catch (Throwable var17) {
                    var6 = var17;
                    throw var17;
                } finally {
                    if (conn != null) {
                        if (var6 != null) {
                            try {
                                conn.close();
                            } catch (Throwable var16) {
                                var6.addSuppressed(var16);
                            }
                        } else {
                            conn.close();
                        }
                    }

                }
            } catch (Exception var19) {

                throw new AlarmException("Cannot write alarm", var19);
            }




            this.fireAlarm(alarmRecord);




    }

    public BIcon getIcon() {
        BValue dynamic = this.get("icon");
        return dynamic instanceof BIcon ? (BIcon)dynamic : icon;
    }

    private static final BIcon icon = BIcon.make(BOrd.make("module://alarmFilter/res/AF.Png"));

}
