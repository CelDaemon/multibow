package net.voidgroup.paper.multibow.bows;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.voidgroup.paper.multibow.Util.translatable;
public class ExplosiveBowType extends BowType {
    public ExplosiveBowType(@NotNull NamespacedKey key) {
        super(key);
    }

    @Override
    public @NotNull Component getName() {
        return translatable("multibow.bowtype.explosive.name");
    }

    @Override
    public @NotNull List<Component> getLore() {
        return List.of(
                translatable("multibow.bowtype.explosive.lore.0", Style.style(NamedTextColor.DARK_PURPLE))
        );
    }

    @Override
    public void onShoot(@NotNull EntityShootBowEvent event) {
        final var oldProjectile = event.getProjectile();
        final var velocity = oldProjectile.getVelocity();
        final var location = oldProjectile.getLocation();
        oldProjectile.remove();
        final var projectile = (TNTPrimed) oldProjectile.getWorld().spawnEntity(location, EntityType.TNT);
        projectile.setFuseTicks(Math.max((int) (event.getForce() * 20), 20));
        projectile.setVelocity(velocity);
        event.setProjectile(projectile);
    }
}
