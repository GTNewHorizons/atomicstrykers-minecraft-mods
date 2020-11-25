package atomicstryker.infernalmobs.common.mods;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.config.Configuration;
import atomicstryker.infernalmobs.common.MobModifier;

public class MM_Storm extends MobModifier
{
    private long nextAbilityUse = 0L;
    private static long coolDown;
    private final static float MIN_DISTANCE = 3F;

    public MM_Storm(EntityLivingBase mob)
    {
        this.modName = "Storm";
    }
    
    public MM_Storm(EntityLivingBase mob, MobModifier prevMod)
    {
        this.modName = "Storm";
        this.nextMod = prevMod;
    }

    @Override
    public boolean onUpdate(EntityLivingBase mob)
    {
        if (getMobTarget() != null
        && getMobTarget() instanceof EntityPlayer)
        {
            tryAbility(mob, getMobTarget());
        }
        
        return super.onUpdate(mob);
    }

    private void tryAbility(EntityLivingBase mob, EntityLivingBase target)
    {
        if (target == null || target.ridingEntity != null || !mob.canEntityBeSeen(target))
        {
            return;
        }
        
        long time = mob.ticksExisted;
        if (time > nextAbilityUse
        && mob.getDistanceToEntity(target) > MIN_DISTANCE
        && target.worldObj.canBlockSeeTheSky(MathHelper.floor_double(target.posX), MathHelper.floor_double(target.posY), MathHelper.floor_double(target.posZ)))
        {
            nextAbilityUse = time+coolDown;
            mob.worldObj.addWeatherEffect(new EntityLightningBolt(mob.worldObj, target.posX, target.posY-1, target.posZ));
        }
    }

    public static void loadConfig(Configuration config)
    {
        coolDown = config.get(MM_Storm.class.getSimpleName(), "coolDownMillis", 15000L, "Time between ability uses").getInt(15000) / 50;
    }

    @Override
    protected String[] getModNameSuffix()
    {
        return suffix;
    }
    private static String[] suffix = { "ofLightning", "theRaiden" };
    
    @Override
    protected String[] getModNamePrefix()
    {
        return prefix;
    }
    private static String[] prefix = { "striking", "thundering", "electrified" };
    
}
