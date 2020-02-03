package com.optimisedbuildingsltd.alarmFilter;

import javax.baja.alarm.BAlarmRecord;
import javax.baja.naming.BOrd;
import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;
import java.util.logging.Logger;
import java.util.regex.Pattern;

@NiagaraType
@NiagaraProperty(
        name = "property",
        type = "BString",
        defaultValue = "BString.make(\"\")"
)
@NiagaraProperty(
        name = "regex",
        type = "BString",
        defaultValue = "BString.make(\"\")"
)


public class BRegexCheck extends BCheck {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.optimisedbuildingsltd.alarmFilter.BRegexCheck(1787189857)1.0$ @*/
/* Generated Tue Jan 07 15:47:13 GMT 2020 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "property"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code property} property.
   * @see #getProperty
   * @see #setProperty
   */
  public static final Property property = newProperty(0, BString.make(""), null);
  
  /**
   * Get the {@code property} property.
   * @see #property
   */
  public String getProperty() { return getString(property); }
  
  /**
   * Set the {@code property} property.
   * @see #property
   */
  public void setProperty(String v) { setString(property, v, null); }

////////////////////////////////////////////////////////////////
// Property "regex"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code regex} property.
   * @see #getRegex
   * @see #setRegex
   */
  public static final Property regex = newProperty(0, BString.make(""), null);
  
  /**
   * Get the {@code regex} property.
   * @see #regex
   */
  public String getRegex() { return getString(regex); }
  
  /**
   * Set the {@code regex} property.
   * @see #regex
   */
  public void setRegex(String v) { setString(regex, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BRegexCheck.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  public Logger logger = Logger.getLogger("ob.alarmFilter");

    public BBoolean doCheck(BAlarmRecord record){
      String[] properties = this.getProperty().split("\\.");
      BObject currentTarget = (BValue)record;

      for(String property : properties){
        logger.finest(currentTarget.toString());
        logger.finest(property);
        try {
          currentTarget = ((BComplex) currentTarget).get(property);
        } catch (ClassCastException cce){
          currentTarget = ((BFacets)currentTarget).get(property);
        }
        if(null == currentTarget){
          return BBoolean.FALSE;
        }
      }
      logger.fine("checking : " + currentTarget.toString());
      boolean check = Pattern.compile(this.getRegex()).matcher(currentTarget.toString()).matches();
      this.setLastCheckValue(check);
        return BBoolean.make(check);
    }

  public BIcon getIcon() {
    BValue dynamic = this.get("icon");
    return dynamic instanceof BIcon ? (BIcon)dynamic : icon;
  }

  private static final BIcon icon = BIcon.make(BOrd.make("module://alarmFilter/res/RX.Png"));
}
