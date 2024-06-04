package me.xxgradzix.gradzixcore.incognito.manages;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.InternalStructure;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.*;
import me.xxgradzix.gradzixcore.Gradzix_Core;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.ClanEntity;
import me.xxgradzix.gradzixcore.clansCore.data.database.entities.UserEntity;
import me.xxgradzix.gradzixcore.clansCore.managers.ClanManager;
import me.xxgradzix.gradzixcore.clansCore.managers.UserManager;
import me.xxgradzix.gradzixcore.incognito.data.database.entities.IncognitoAdminEntity;
import me.xxgradzix.gradzixcore.incognito.data.database.entities.IncognitoModeEntity;
import me.xxgradzix.gradzixcore.incognito.data.database.managers.IncognitoAdminEntityManager;
import me.xxgradzix.gradzixcore.incognito.data.database.managers.IncognitoModeEntityManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.security.SecureRandom;
import java.util.*;

import static com.comphenix.protocol.ProtocolLibrary.*;

public class IncognitoModeManager {

    private static final Gradzix_Core plugin = Gradzix_Core.getInstance();

    private static IncognitoModeEntityManager incognitoModeEntityManager;
    private static IncognitoAdminEntityManager incognitoAdminEntityManager;

    public IncognitoModeManager(IncognitoModeEntityManager incognitoModeEntityManager, IncognitoAdminEntityManager incognitoAdminEntityManager) {
        this.incognitoModeEntityManager = incognitoModeEntityManager;
        this.incognitoAdminEntityManager = incognitoAdminEntityManager;
        changeNicks();
    }

    public static void toggleIncognitoMode(Player player) {

        String incognitoNick = generateRandomString(11);
        IncognitoModeEntity incognitoModeEntity = incognitoModeEntityManager.getIncognitoModeEntityById(player.getUniqueId());

        if(incognitoModeEntity == null) {
            incognitoModeEntity = new IncognitoModeEntity(player.getUniqueId(), incognitoNick, false);
            incognitoModeEntityManager.createIncognitoModeEntity(incognitoModeEntity);
        }
        incognitoModeEntity.setIncognitoNick(incognitoNick);
        if(incognitoModeEntity.isIncognitoModeEnabled()) {
            player.sendMessage("§aWyłaczono tryb incognito!");
        } else {
            player.sendMessage("§aWłączono tryb incognito!");
        }
        incognitoModeEntity.setIncognitoModeEnabled(!incognitoModeEntity.isIncognitoModeEnabled());
        incognitoModeEntityManager.createOrIncognitoModeEntity(incognitoModeEntity);
        refreshNick(player);
        setPrefix(player);
    }

    private static WrappedSignedProperty getSkin() {
        Random r = new Random();
        int num = r.nextInt(4);
        switch (num) {
//            case 0: return new WrappedSignedProperty("textures", "eyJ0aW1lc3RhbXAiOjE0ODI1MjE4MTU4MTQsInByb2ZpbGVJZCI6IjQzYTgzNzNkNjQyOTQ1MTBhOWFhYjMwZjViM2NlYmIzIiwicHJvZmlsZU5hbWUiOiJTa3VsbENsaWVudFNraW42Iiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82ODQ1NzU2ODI5YjZiY2E1MTZiNWJmOTI1MWFlMzFjNzljZDZkZGJjM2M1N2YxMTkzNzBiMGNjZDhkNmY1YTEifX19", "SKrjFrc/FJfl+xSG1/gsjcPMkEWhHkme527T3dTZIXtofzunAQ8VqZcPu9NJmwOCOlvqRL8T0STwSGaNZctHkh5+5xVKsS6w8/oe3rbdz+7g854C/p5Op7xCH0H/HhEB1HgVqQx5ZOWFuaGn2EaPIdx1v/9z//TG/SyCeNeYsiafJcETDFTeFLxl+L+RpyMxjBlTwPD1vQPrt2VZ8PLfpMlndbQUuquPhCeoYRNil9fqjYjNJHSnc9URjGfpBVZk/XCb+F6i3ljkbv9OChSgPUhli9ktckVnyFQmkImOq1eviyThh2pjg9qV7peaU9dxNyNazpf40B82X4Wztor62Y14DJXaGzUZcQN6oMbr/L8xjEwXRXuWBt9Szemi7ZZIpGXR00GSqeEW20+C5ZiwbsjzmuLxDw876FG/w76U2T9Z1joEf4ef+c13Byc+9KVXBX3ybhTerrXkW+oXbx2XBRZ5K5cOHmlcFT7rmR9iiXTcA2smB4eFxyHqgITLK/28aWSGyQFjZJMLxSr0EP0I0yrMil8tXggTZFq7kFeO32Ehr1unqGQet3kjCnk6z3vNUAnW6PmzmATwEaGEvOeIpBufq7EnylBK/UzpqoGTkn47zEXBHXY7m+BT2PwFSEF0D0X63h8SHx+G5hiSbcH6BRG1i1OLi7GofuadMHEPddI=");
//            case 1: return new WrappedSignedProperty("textures", "ewogICJ0aW1lc3RhbXAiIDogMTYwODI1NDU1NDk0OSwKICAicHJvZmlsZUlkIiA6ICIzM2ViZDMyYmIzMzk0YWQ5YWM2NzBjOTZjNTQ5YmE3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJEYW5ub0JhbmFubm9YRCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9kYWQzM2FiNjRjODFiNDNiZjRiZjUyNjFkZmRkODRiODEyOTE1MmRkN2IxMjI2MWY1ZGFjYjc3MzYyOTIyYTVkIgogICAgfQogIH0KfQ==", "NWkfC4LjPAHCY5mvfjZ/QmtnvAJ8GOQnlg8U6k10si6e545TSNi5XpNIXS1cTuRdSoZ3mzWm4a00Cob6bOxb7G5o115UcX2rFzAT6nRNnAIkDkdYi3NxFcdip+pNDdOV+y+QR4J4VpZAwoMaGCsKyIMoT8CYeyexIO+ifYMpwZsEsLRlWaz+GNOes7Ql6+3aONsZuU9qTiRCRXyqBlDAWV3DyNToJDoudQ0Hl0L5KGbR4hUB913JHTlEuBfcRT3mHNmOrzMp9Zdr08q5SA2Fn1IZ9gMr+Dj1K+NEjkzyyRbZkjHR+CSzXajBvIztgGsdb0ZOPDckuPabVBvIQrHVj9Wq4A7u1z8H2cUroVOaH0ePIwi5YSCkeXW3sw8dyS4XSd7x6upjhAJaJ4hkrw8FFTeKYo96LbqROkO57A5fr8d/f+l0pfR4uSJtKtSMeeSkqPmJQsPWYx9tWeqSDEsr3HGekxO/hcuBrlSmyXjhmzdIO1zTUmWTxqaCfFxDx6JFan8QcbVCBxFm9Ro8KnTIOfcc+np82N4oFo67wZwZYOo27oV8NzKbwvRukdQGSNfT4xHwWMLR8wNsBUcOvoTco+lZDNL1Er1a4nKTigURPiokOVsnwvGR1B8nuE5xAil5F22644Yt8NQkzO06CcLFShw9FrLfbb3Znexp1VC2ztg=");
//            case 2: return new WrappedSignedProperty("textures", "eyJ0aW1lc3RhbXAiOjE1NzUzMzg1MjYxMjUsInByb2ZpbGVJZCI6IjJjY2I1NDYxYmNhMDQ4YzE4ZGU4MjIwYWMzMjQ4ZWY3IiwicHJvZmlsZU5hbWUiOiJSZWluZGVlciIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjNiYjBjMzQ5M2M4YzdkOWE3NDAxNTRmOTMyMjhmM2JjMjkzZTA1ZWZlZTkyMzQwMTk2YzI0MTMzMGUxYTJkMiJ9fX0=", "LB3VTkXygcg9/8RrWZLjtz0vf+KeDHGiJIyX47KDScVNSvvOOykFy18ncKioGV1yXdXyPlyIa5b9vu/Mfhn+21ykDaMtzbwfgQ4K0VSyXYyH7aLpHGB3izE26ghYULfqUKMEAH6uz3SGrFW9UbsOv6YdNI4tSxvBMetgsRU46vf2QcSRRgw+7ap3ZBNRNG8ca62u+a4HiC+5bMVgRwOM6YOSrw7MXtJjNlz3bsS0we8okUhCMq+my2mcl1XTF34UIogCYFk2R7LgDP2Fzkh/f1srt2TMNjMtFoKlmXy3/GVMW1sM3plKGVcfARMJYTpVI+Tsq4Wrze+yzK89dvm/x0N71MpkDgdZxTnTFu7EsFfml1nyM4RwE2h0LU2K6BNm8UBShb5plD8PnuBNVuQJzG0IvVoRJWKqRjGAJL4qgLxSjgVWjsAbXBEgqsx8b2MIFWHSXkiycIW7ZZBhP/LAtIAsLaxFbnidpPBR7rdBQ7ERyqrGTDc1DLBRUpGTtQb4Q4kF0y0wh5gZA05wvn85fh+qAu8HxEo0vE5I+Wa+v5b8Uo0Udsby0yqEZXxGQERNXEYK6BPmu1CKu5OZJJ8DOe0JWZs8Lu0NYWnvyQkSRlhLZqePW8B9nXCHyWFTaSRoO3FB5VisTpzayMBP4v86fLI3CUbyb0hEPdZe0TV77Zs=");
//            default: return new WrappedSignedProperty("textures", "ewogICJ0aW1lc3RhbXAiIDogMTYwNjc5MDYyMTExOCwKICAicHJvZmlsZUlkIiA6ICJlZDUzZGQ4MTRmOWQ0YTNjYjRlYjY1MWRjYmE3N2U2NiIsCiAgInByb2ZpbGVOYW1lIiA6ICI0MTQxNDE0MWgiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODlhNDFmMGRiYzcyOTYyZWFlNzExODcwZTkyYTc3YmNkMDFjZGNjNmE2ZDQwZTExZTk2NDYzOWMyMWIyYjVmOSIKICAgIH0KICB9Cn0=", "pegOuK3kV049MM5A0yKTwMxUu2f9jOB14FMkWkXs9GG7f7KsIssOj1eRt/39j7aW9U+M4pULYM2CG6rlpyih6Qofw26IHubG4P594ybhIKxtMbSEbOBIl7xkZI86hul/3odPvwkghgngsp3aY1jfgTNVjfROF9rOVGfugkWdXvYX/+CJs+pKkwdkKZpeUmSSwlB7p0MGvKCY8LuBms4/g0k8ipTOfEK4upOLHD/vBA01GHX+Epspd8tKvucIJLD+VANu12DmT0d2JZKrIyga4zuH4phFqnlg3JoNBurgJhAsDSXWuOnQRtcDqv8KOg/W8KRuvmAP4TZB1amAyPND72nmteF9YinN0KH1QkBKb6d4gvkP/0RKz562Z9+srZnlxBBrJ5V7JpT7kshQHYHP4qy6zE8De/vomJa23GB0T1lQai2a/p0uOCMk+oXu7aKuf0MrxaFptEN+q2VYJBz+9l6VRxRyJJZhFA0Yc0o2eD9zeqEQNTulbY17VP1bIU06Fxuo+S2AMjdV3a+IMaYo8FGx6I5H5rGXrL3Uqnu5IJFXAFN1eHW2RulOpvWO1SUvUYQUTCIestFB87quWCINUFk/DjUGysEZ+7E6q5kNM75SaTCFsMmDI0wFCCRPD6Hv6kcHNu+xx7Bezse75kUsdSiNVyNC4B3mVNMBuBFn/Nw=");
            case 0: return new WrappedSignedProperty("textures", "ewogICJ0aW1lc3RhbXAiIDogMTYyMTM1ODY0MDM0OSwKICAicHJvZmlsZUlkIiA6ICJmYzUwMjkzYTVkMGI0NzViYWYwNDJhNzIwMWJhMzBkMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJDVUNGTDE3IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2Y0ZTk5MWU1ZDRjZjljNTJiYTYzNGU0NzMyMmJlMDhlNTc4NzQ4MGJiMTczNzFhYjJjYTQyNTEyNzFiYzA3NmIiCiAgICB9CiAgfQp9", "OvYpPRr4JBiP4wnZDI+9ehKXEsk2/EhpF8kcmIABfimyb/dCC+VQ5vnLf+y7sdJ7+x3UCaEX5Hrc5FGY9AjOIpexcb2E98AOjm4Gb43D91r9CAzFrxzVGWKC0fNJS7VnFWBHm8QC6WRTlN8mx+zYmES/spUOJxQHbohDVc1fnlUMwE6EiPg2MedLdcAqYa7KDBgrroMd2vH4/E9mTww4Z1QYVPclnTG5X6bEQYRqfkFBd+qquiLrZo4PvdqDw1nrbh6jEmHxAD86zW3cSFcZikCpUOD4R6DwI+nRFZRax2ferCQI2CJchewUC84GsVPG8vIH0Ayx5vnZYWOeed2ISztgEocuIPWmTdFb+mK0WVJVQKZ92YtYfGwONaCcaaBag/keLCz/UKLVN/7WB/XHvCcXScW/+wi0cxpiBifcJMV5NU12AgVZpGR4TOFDZ2wlNm4qnRaV6ZWqlrAR2fayJk6703kiv2Agmoxmj1KEbHe9uOWLiEuMNCPlOGksnGM3mzM645J0wwt/kyMJP5g59zV1xEym7QRa+wd2O9g7oV00w+GtpmWB3r6EqykqDQDVv6F9D6xnRKOQ08jLeiHpWCW8+Eva9xSF8I16eeMtQBFOgI8JNkd/5DBHIjYaoeyVx9fci15Nzcp01bkNPBkMpR94kco7wFhgVA6eoOuD9Xo=");
            case 1: return new WrappedSignedProperty("textures", "ewogICJ0aW1lc3RhbXAiIDogMTYyMzY4MzM1ODEyOCwKICAicHJvZmlsZUlkIiA6ICIyM2YxYTU5ZjQ2OWI0M2RkYmRiNTM3YmZlYzEwNDcxZiIsCiAgInByb2ZpbGVOYW1lIiA6ICIyODA3IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzI0NjYzNzE0ZTE2YTA4ZGFkZmJiNWUyZGI1MTUwNmQzNjZmZTM4NzQxZTYzZDM3ZjNmOGVjZWJkM2ZhZWYyZjIiCiAgICB9CiAgfQp9", "NeK2SsrhmFttysRtryG4vy3FrSV0Mnn113+0Tk2Ztya9qOwPxmeu87iHFLFzVsqBw0T8o7ubhy7xJaWwm5Qf515VsY07bVzjMVzf9mJNM08De+Hes3iDPcZAUpprNYou33MIDwmz/O3QNgIh2rRusPZtmuMpirpM6yD5GFnBcRZ/U+JeEGq9EgzDh76Agem8taeyVp3QM4eIMPJhGnsbhHqH3OEmvPeERfOdfdPDW9UWv5td/uXv66MQ6s37xpiNo6t85fwIf2NyBGKIaT496wKzdeD2D7zUfwx6OKalrEfsM2KjLnZKiTqvPk/nXI1W63tuXyKmnbyFSF/VAMTHNxc0iKvLYDAUsPP5hr/0JCooMCOHPvuwvmTyyFhgO9OeVDzaLE+aMyJa6RXnTBolYxreAetffIDsa30rv/pPTj8j1Y3WPr1LL0SDhXSSK2kv3IoIE7KzpUIIeCaJBDjMIiRyO+CEP0cP0a5jR6gWmsf9cNtIwBtUY1Smi4i0qgIJLE2btH2h/dxdipircJJu5ACHb0ZWorARRGGvihtq2P7GypPknWPQo/9JwM+r3DbLxzrlY0sY70Jcn3Dnjo9PmYpph1Li2KsHi7UM4t28RjNebJFNx2IuafHDoTOUse/Dw4FAO1ZdKa6aW4k15g9+GI+xB6zss1uYw4ky1qoklA8=");
            case 2: return new WrappedSignedProperty("textures", "ewogICJ0aW1lc3RhbXAiIDogMTYyMTM1ODY0MDM0OSwKICAicHJvZmlsZUlkIiA6ICJmYzUwMjkzYTVkMGI0NzViYWYwNDJhNzIwMWJhMzBkMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJDVUNGTDE3IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2Y0ZTk5MWU1ZDRjZjljNTJiYTYzNGU0NzMyMmJlMDhlNTc4NzQ4MGJiMTczNzFhYjJjYTQyNTEyNzFiYzA3NmIiCiAgICB9CiAgfQp9", "OvYpPRr4JBiP4wnZDI+9ehKXEsk2/EhpF8kcmIABfimyb/dCC+VQ5vnLf+y7sdJ7+x3UCaEX5Hrc5FGY9AjOIpexcb2E98AOjm4Gb43D91r9CAzFrxzVGWKC0fNJS7VnFWBHm8QC6WRTlN8mx+zYmES/spUOJxQHbohDVc1fnlUMwE6EiPg2MedLdcAqYa7KDBgrroMd2vH4/E9mTww4Z1QYVPclnTG5X6bEQYRqfkFBd+qquiLrZo4PvdqDw1nrbh6jEmHxAD86zW3cSFcZikCpUOD4R6DwI+nRFZRax2ferCQI2CJchewUC84GsVPG8vIH0Ayx5vnZYWOeed2ISztgEocuIPWmTdFb+mK0WVJVQKZ92YtYfGwONaCcaaBag/keLCz/UKLVN/7WB/XHvCcXScW/+wi0cxpiBifcJMV5NU12AgVZpGR4TOFDZ2wlNm4qnRaV6ZWqlrAR2fayJk6703kiv2Agmoxmj1KEbHe9uOWLiEuMNCPlOGksnGM3mzM645J0wwt/kyMJP5g59zV1xEym7QRa+wd2O9g7oV00w+GtpmWB3r6EqykqDQDVv6F9D6xnRKOQ08jLeiHpWCW8+Eva9xSF8I16eeMtQBFOgI8JNkd/5DBHIjYaoeyVx9fci15Nzcp01bkNPBkMpR94kco7wFhgVA6eoOuD9Xo=");
            default: return new WrappedSignedProperty("textures", "ewogICJ0aW1lc3RhbXAiIDogMTYyMzY4MzM1ODEyOCwKICAicHJvZmlsZUlkIiA6ICIyM2YxYTU5ZjQ2OWI0M2RkYmRiNTM3YmZlYzEwNDcxZiIsCiAgInByb2ZpbGVOYW1lIiA6ICIyODA3IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzI0NjYzNzE0ZTE2YTA4ZGFkZmJiNWUyZGI1MTUwNmQzNjZmZTM4NzQxZTYzZDM3ZjNmOGVjZWJkM2ZhZWYyZjIiCiAgICB9CiAgfQp9", "NeK2SsrhmFttysRtryG4vy3FrSV0Mnn113+0Tk2Ztya9qOwPxmeu87iHFLFzVsqBw0T8o7ubhy7xJaWwm5Qf515VsY07bVzjMVzf9mJNM08De+Hes3iDPcZAUpprNYou33MIDwmz/O3QNgIh2rRusPZtmuMpirpM6yD5GFnBcRZ/U+JeEGq9EgzDh76Agem8taeyVp3QM4eIMPJhGnsbhHqH3OEmvPeERfOdfdPDW9UWv5td/uXv66MQ6s37xpiNo6t85fwIf2NyBGKIaT496wKzdeD2D7zUfwx6OKalrEfsM2KjLnZKiTqvPk/nXI1W63tuXyKmnbyFSF/VAMTHNxc0iKvLYDAUsPP5hr/0JCooMCOHPvuwvmTyyFhgO9OeVDzaLE+aMyJa6RXnTBolYxreAetffIDsa30rv/pPTj8j1Y3WPr1LL0SDhXSSK2kv3IoIE7KzpUIIeCaJBDjMIiRyO+CEP0cP0a5jR6gWmsf9cNtIwBtUY1Smi4i0qgIJLE2btH2h/dxdipircJJu5ACHb0ZWorARRGGvihtq2P7GypPknWPQo/9JwM+r3DbLxzrlY0sY70Jcn3Dnjo9PmYpph1Li2KsHi7UM4t28RjNebJFNx2IuafHDoTOUse/Dw4FAO1ZdKa6aW4k15g9+GI+xB6zss1uYw4ky1qoklA8=");
        }
    }

    public static void refreshNick(Player player) {
//        WrappedGameProfile profile = WrappedGameProfile.fromPlayer(player);
        int entityId = player.getEntityId();

        for (Player online : Bukkit.getOnlinePlayers()) {
            sendPlayerInfoPacket(online, player, EnumWrappers.PlayerInfoAction.REMOVE_PLAYER);
            sendPlayerInfoPacket(online, player, EnumWrappers.PlayerInfoAction.ADD_PLAYER);
            if (!online.equals(player)) {
                sendEntityDestroyPacket(online, entityId);
                sendNamedEntitySpawnPacket(online, player);
            }
        }
    }

    private static void sendPlayerInfoPacket(Player recipient, Player player, EnumWrappers.PlayerInfoAction action) {
        PacketContainer packet = getProtocolManager().createPacket(PacketType.Play.Server.PLAYER_INFO);
        packet.getPlayerInfoAction().write(0, action);
        packet.getPlayerInfoDataLists().write(0, Collections.singletonList(
                new PlayerInfoData(WrappedGameProfile.fromPlayer(player), 0, EnumWrappers.NativeGameMode.SURVIVAL, WrappedChatComponent.fromText(player.getDisplayName()))
        ));
        try {
            getProtocolManager().sendServerPacket(recipient, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendEntityDestroyPacket(Player recipient, int entityId) {
        PacketContainer packet = getProtocolManager().createPacket(PacketType.Play.Server.ENTITY_DESTROY);
        packet.getIntLists().write(0, Collections.singletonList(entityId));
        try {
            getProtocolManager().sendServerPacket(recipient, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendNamedEntitySpawnPacket(Player recipient, Player player) {
        PacketContainer packet = getProtocolManager().createPacket(PacketType.Play.Server.NAMED_ENTITY_SPAWN);
        packet.getIntegers().write(0, player.getEntityId());
        packet.getUUIDs().write(0, player.getUniqueId());
        packet.getDoubles().write(0, player.getLocation().getX());
        packet.getDoubles().write(1, player.getLocation().getY());
        packet.getDoubles().write(2, player.getLocation().getZ());
        packet.getBytes().write(0, (byte) (player.getLocation().getYaw() * 256 / 360));
        packet.getBytes().write(1, (byte) (player.getLocation().getPitch() * 256 / 360));
        try {
            getProtocolManager().sendServerPacket(recipient, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void setPrefix(Player player) {
        ProtocolManager protocolManager = getProtocolManager();

        IncognitoModeEntity incognitoModeEntityById = incognitoModeEntityManager.getIncognitoModeEntityById(player.getUniqueId());

        if(incognitoModeEntityById == null) return;
        if(!incognitoModeEntityById.isIncognitoModeEnabled()) return;

        PacketContainer packet = protocolManager.createPacket(PacketType.Play.Server.SCOREBOARD_TEAM); // Create a new scoreboard team packet

        packet.getIntegers().write(0,0); //Mode -create team

        packet.getStrings().write(0, incognitoModeEntityById.getIncognitoNick()); // Give the team a name

        Optional<InternalStructure> optStruct = packet.getOptionalStructures().read(0); //Team Data

        if (optStruct.isPresent()) {

            InternalStructure struct = optStruct.get();

            struct.getChatComponents().write(0, WrappedChatComponent.fromText(incognitoModeEntityById.getIncognitoNick()));//TeamName

            struct.getChatComponents().write(2, WrappedChatComponent.fromText(" §8[§7" + player.getName() + "§8]"));//Team Suffix
            struct.getIntegers().write(0, 1); // Bit mask. 0x01: Allow friendly fire, 0x02: can see invisible players on same team.
            packet.getOptionalStructures().write(0, Optional.of(struct));

        }

        String incognitoNick = incognitoModeEntityById.getIncognitoNick();

        packet.getModifier().write(2, Collections.singletonList(incognitoNick));

        for(IncognitoAdminEntity incognitoAdminEntity: incognitoAdminEntityManager.getAllIncognitoAdminEntities()) {
            Player observer = Bukkit.getPlayer(incognitoAdminEntity.getUuid());

            if(observer == null) continue;
            if(observer.equals(player)) continue;

            // Send the packet to the player
            protocolManager.sendServerPacket(observer, packet);
        }
    }


    private static void changeNicks() {

        getProtocolManager().addPacketListener(new PacketAdapter(plugin, PacketType.Play.Server.PLAYER_INFO) {

            @Override
            public void onPacketSending(PacketEvent event) {
                if (event.getPacket().getPlayerInfoAction().read(0) != EnumWrappers.PlayerInfoAction.ADD_PLAYER) return;
                List<PlayerInfoData> newPlayerInfoDataList = new ArrayList<>();
                for (PlayerInfoData playerInfoData : event.getPacket().getPlayerInfoDataLists().read(0)) {
                    if (playerInfoData == null || playerInfoData.getProfile() == null || Bukkit.getPlayer(playerInfoData.getProfile().getUUID()) == null) { //Unknown Player
                        newPlayerInfoDataList.add(playerInfoData);
                        continue;
                    }
                    WrappedGameProfile profile = playerInfoData.getProfile();
                    Player player = Bukkit.getPlayer(profile.getUUID());
                    if (player == null) return;
                    IncognitoModeEntity incognitoModeEntity = incognitoModeEntityManager.getIncognitoModeEntityById(player.getUniqueId());
                    if (incognitoModeEntity == null) return;
                    if(!incognitoModeEntity.isIncognitoModeEnabled()) return;
                    String displayName;
                    Player observer = event.getPlayer();
                    displayName = getIncognitoNickColor(observer, player) + incognitoModeEntity.getIncognitoNick();
                    profile = profile.withName(displayName);
                    PlayerInfoData newPlayerInfoData = new PlayerInfoData(profile, playerInfoData.getPing(), playerInfoData.getGameMode(), WrappedChatComponent.fromText(displayName));
                    newPlayerInfoData.getProfile().getProperties().removeAll("textures");
                    newPlayerInfoData.getProfile().getProperties().put("textures", getSkin());
                    newPlayerInfoDataList.add(newPlayerInfoData);
                }
                event.getPacket().getPlayerInfoDataLists().write(0, newPlayerInfoDataList);
            }
        });
    }

    private static String getIncognitoNickColor(Player observer, Player player) {
        boolean isObserverAdmin = incognitoAdminEntityManager.getAllIncognitoAdminEntities().stream()
                .map(IncognitoAdminEntity::getUuid)
                .anyMatch(uuid -> uuid.equals(observer.getUniqueId()));
        if(isObserverAdmin) return "";
        UserEntity playerUser = UserManager.getOrCreateUserEntity(player);
        UserEntity observerUser = UserManager.getOrCreateUserEntity(observer);
        if(!ClanManager.getClanEntityOfMember(playerUser).isPresent()) return "§f"; // gracz nie ma gildii
        if(!ClanManager.getClanEntityOfMember(observerUser).isPresent()) return "§7"; // gracz ma gildie ale obserwator nie ma = szary
        ClanEntity clanEntity = ClanManager.getClanEntityOfMember(playerUser).get();
        ClanEntity observerClan = ClanManager.getClanEntityOfMember(observerUser).get();
        if(clanEntity.equals(observerClan)) return "§a"; // gracz i obserwator maja ta sama gildie = zielony
        if(clanEntity.getAlliesUUIDs().contains(observerClan.getUuid())) return "§9"; // gracz i obserwator maja sojusznice = niebieski
        if(clanEntity.getEnemiesUUIDs().contains(observerClan.getUuid())) return "§c"; // gracz i obserwator maja wrogow = czerwony
        return "";
    }

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static String generateRandomString(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }
}
