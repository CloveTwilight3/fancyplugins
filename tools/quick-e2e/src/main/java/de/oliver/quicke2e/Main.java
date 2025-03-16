package de.oliver.quicke2e;

import de.oliver.quicke2e.config.Configuration;
import de.oliver.quicke2e.config.Context;
import de.oliver.quicke2e.steps.copyFile.CopyFileService;
import de.oliver.quicke2e.steps.eula.EulaService;
import de.oliver.quicke2e.steps.gradle.GradleService;
import de.oliver.quicke2e.steps.ops.OPsService;
import de.oliver.quicke2e.steps.paper.PaperDownloadService;
import de.oliver.quicke2e.steps.startScript.StartScriptService;
import de.oliver.quicke2e.steps.startServer.StartServerService;

public class Main {

    public static void main(String[] args) {
        Configuration config = new Configuration(
                "paper",
                "1.21.4",
                "latest",
                new String[]{},
                true,
                new String[]{"OliverHD"},
                "25565"
        );

        Context context = new Context(config);

        PaperDownloadService paper = new PaperDownloadService();
        paper.downloadServerFile(context);

        EulaService eula = new EulaService();
        if (config.eula()) {
            eula.setEulaToTrue(String.format("%s/eula.txt", context.serverEnvPath().toString()));
        }

        OPsService ops = new OPsService();
        ops.makePlayersOP(context);

        StartScriptService startScript = new StartScriptService();
        startScript.writeStartScript(context);

        GradleService gradle = new GradleService();
//        gradle.runTask(":plugins:fancyholograms:shadowJar");
//        gradle.runTask(":plugins:fancyvisuals:shadowJar");

        CopyFileService copyFile = new CopyFileService();
        copyFile.copyFile(context, "plugins/fancyholograms/build/libs", "FancyHolograms-.*\\.jar", "plugins");
        copyFile.copyFile(context, "plugins/fancyvisuals/build/libs", "FancyVisuals-.*\\.jar", "plugins");

        StartServerService startServer = new StartServerService();
        startServer.startServer(context);
    }

}
