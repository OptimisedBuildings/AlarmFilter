package com.optimisedbuildingsltd.alarmFilter;

import javax.baja.alarm.BAlarmRecord;
import javax.baja.license.Feature;
import javax.baja.license.LicenseManager;
import javax.baja.naming.BOrd;
import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;
import java.util.logging.Logger;

@NiagaraType
@NiagaraAction(
        name = "routeAlarm",
        parameterType = "BAlarmRecord",
        defaultValue = "new BAlarmRecord()"
)
@NiagaraProperty(
        name = "licensed",
        type = "BBoolean",
        defaultValue = "BBoolean.make(false)",
        flags = Flags.READONLY
)

public class BAlarmFilteringModule extends BComponent {
    
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.optimisedbuildingsltd.alarmFilter.BAlarmFilteringModule(1603159223)1.0$ @*/
/* Generated Mon Feb 10 16:36:37 GMT 2020 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "licensed"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code licensed} property.
   * @see #getLicensed
   * @see #setLicensed
   */
  public static final Property licensed = newProperty(Flags.READONLY, ((BBoolean)(BBoolean.make(false))).getBoolean(), null);
  
  /**
   * Get the {@code licensed} property.
   * @see #licensed
   */
  public boolean getLicensed() { return getBoolean(licensed); }
  
  /**
   * Set the {@code licensed} property.
   * @see #licensed
   */
  public void setLicensed(boolean v) { setBoolean(licensed, v, null); }

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

    private Logger logger = Logger.getLogger("ob.alarmFilter");

    private boolean checkLicense(){
        LicenseManager lm = Sys.getLicenseManager();
        Feature[] features = lm.getFeatures();
        boolean licensed = false;
        for(Feature feature : features){
            if(feature.getVendorName().equals("Optimised") && feature.getFeatureName().equals("oblAlarmFilter")){
                licensed = true;
            }
        }
        this.setLicensed(licensed);
        return licensed;
    }

    public void doRouteAlarm(BAlarmRecord record){
        //Check License
        if(!this.checkLicense()){
            logger.warning("Not Licensed");
            return;
        }

        //Method simply passes alarms to call AlarmFilter children
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