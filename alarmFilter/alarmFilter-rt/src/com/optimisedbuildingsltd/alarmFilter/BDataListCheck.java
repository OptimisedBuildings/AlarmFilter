package com.optimisedbuildingsltd.alarmFilter;

import javax.baja.alarm.BAlarmRecord;
import javax.baja.naming.BOrd;
import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraTopic;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;
import java.util.logging.Logger;

@NiagaraType
@NiagaraProperty(
        name = "dataList",
        type = "BDataList",
        defaultValue = "new BDataList()"
)
@NiagaraProperty(
        name = "property",
        type = "BString",
        defaultValue = "BString.make(\"\")"
)




public class BDataListCheck extends BCheck {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.optimisedbuildingsltd.alarmFilter.BDataListCheck(4130902374)1.0$ @*/
/* Generated Tue Jan 07 15:47:27 GMT 2020 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "dataList"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code dataList} property.
   * @see #getDataList
   * @see #setDataList
   */
  public static final Property dataList = newProperty(0, new BDataList(), null);
  
  /**
   * Get the {@code dataList} property.
   * @see #dataList
   */
  public BDataList getDataList() { return (BDataList)get(dataList); }
  
  /**
   * Set the {@code dataList} property.
   * @see #dataList
   */
  public void setDataList(BDataList v) { set(dataList, v, null); }

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
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BDataListCheck.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  private Logger logger = Logger.getLogger("ob.alarmFilter");

    public BBoolean doCheck(BAlarmRecord record){
      //Example uses of this are controller ID's, site numbers, priorities
      //Returns true if property contains a value found in the list.
      logger.info("matching '" + record.get(this.getProperty()).toString() + "' to '" + this.getDataList() + "'");
        boolean check = this.getDataList().matchesItemInArray(record.get(this.getProperty()).toString());
        this.setLastCheckValue(check);
        return BBoolean.make(check);
    }

  public BIcon getIcon() {
    BValue dynamic = this.get("icon");
    return dynamic instanceof BIcon ? (BIcon)dynamic : icon;
  }

  private static final BIcon icon = BIcon.make(BOrd.make("module://alarmFilter/res/DL.Png"));
}
