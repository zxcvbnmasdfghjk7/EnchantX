package io.github.eddiediamondfire.cne.Commands;

import io.github.eddiediamondfire.cne.Commands.SubCommandWrappers.SubCommandCNE;

import java.util.Iterator;

public class CommandMethods {

    public static SubCommandCNE get(String name) {
        Iterator<SubCommandCNE> subcommands = MainCMDManager.subCommandCNE.iterator();

        while (subcommands.hasNext()) {
            SubCommandCNE sc = (SubCommandCNE) subcommands.next();

            if (sc.name().equalsIgnoreCase(name)) {
                return sc;
            }

            String[] aliases;
            int length = (aliases = sc.aliases()).length;

            for (int var5 = 0; var5 < length; ++var5) {
                String alias = aliases[var5];
                if (name.equalsIgnoreCase(alias)) {
                    return sc;
                }

            }
        }
        return null;
    }
}
