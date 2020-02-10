package com.optimisedbuildingsltd.alarmFilter;

import javax.baja.alarm.BAlarmRecord;
import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;
import java.util.logging.Logger;

@NiagaraType
@NiagaraAction(
        name = "check",
        parameterType = "BAlarmRecord",
        defaultValue = "new BAlarmRecord()",
        returnType = "BBoolean"
)
@NiagaraProperty(
        name = "lastCheckValue",
        type =  "BBoolean",
        defaultValue = "BBoolean.make(false)"
)

public class BCheck extends BComponent {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.optimisedbuildingsltd.alarmFilter.BCheck(2935600785)1.0$ @*/
/* Generated Wed Jan 08 09:00:42 GMT 2020 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "lastCheckValue"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code lastCheckValue} property.
   * @see #getLastCheckValue
   * @see #setLastCheckValue
   */
  public static final Property lastCheckValue = newProperty(0, ((BBoolean)(BBoolean.make(false))).getBoolean(), null);
  
  /**
   * Get the {@code lastCheckValue} property.
   * @see #lastCheckValue
   */
  public boolean getLastCheckValue() { return getBoolean(lastCheckValue); }
  
  /**
   * Set the {@code lastCheckValue} property.
   * @see #lastCheckValue
   */
  public void setLastCheckValue(boolean v) { setBoolean(lastCheckValue, v, null); }

////////////////////////////////////////////////////////////////
// Action "check"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code check} action.
   * @see #check(BAlarmRecord parameter)
   */
  public static final Action check = newAction(0, new BAlarmRecord(), null);
  
  /**
   * Invoke the {@code check} action.
   * @see #check
   */
  public BBoolean check(BAlarmRecord parameter) { return (BBoolean)invoke(check, parameter, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BCheck.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  public Logger logger = Logger.getLogger("ob.alarmFilter");

    public BBoolean doCheck(BAlarmRecord record){
      //Generic class so other components can inherit. Could have been done with an interface
      this.setLastCheckValue(false);
        return BBoolean.FALSE;
    }
}
