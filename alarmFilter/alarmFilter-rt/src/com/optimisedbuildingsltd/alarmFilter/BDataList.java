package com.optimisedbuildingsltd.alarmFilter;

import javax.baja.nre.annotations.Facet;
import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;

import javax.baja.sys.*;
import java.util.logging.Logger;

@NiagaraType
@NiagaraProperty(
        name = "dataArray",
        type = "BString",
        defaultValue = "BString.make(\"\")",
        flags = Flags.READONLY
)
@NiagaraAction(
        name = "updateArray",
        parameterType = "BString",
        defaultValue = "BString.make(\"\")"
)

public class BDataList extends BComponent {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.optimisedbuildingsltd.alarmFilter.BDataList(3976423038)1.0$ @*/
/* Generated Tue Jan 07 14:19:40 GMT 2020 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "dataArray"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code dataArray} property.
   * @see #getDataArray
   * @see #setDataArray
   */
  public static final Property dataArray = newProperty(Flags.READONLY, BString.make(""), null);
  
  /**
   * Get the {@code dataArray} property.
   * @see #dataArray
   */
  public String getDataArray() { return getString(dataArray); }
  
  /**
   * Set the {@code dataArray} property.
   * @see #dataArray
   */
  public void setDataArray(String v) { setString(dataArray, v, null); }

////////////////////////////////////////////////////////////////
// Action "updateArray"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code updateArray} action.
   * @see #updateArray(BString parameter)
   */
  public static final Action updateArray = newAction(0, BString.make(""), null);
  
  /**
   * Invoke the {@code updateArray} action.
   * @see #updateArray
   */
  public void updateArray(BString parameter) { invoke(updateArray, parameter, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BDataList.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    private Logger logger = Logger.getLogger("ob.alarmFilter");

    public void doUpdateArray(BString input){
        this.setDataArray(input.getString());
    }

    public BDataList(){

    }

    public BDataList(String data){
        this.setDataArray(data);
    }

    public boolean matchesItemInArray(String test){

        //Contains an array of values. This is used to check if the property contains an item on the list.
        //Example uses of this are controller ID's, site numbers, priorities
        String[] array = this.getDataArray().split(",");
        for(String item : array){
            logger.info("does " + test + "contain" + item + "?");
            if(test.contains(item)){
                return true;
            }
        }
        return false;
    }

}
