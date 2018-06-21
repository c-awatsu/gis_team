package page;

import answer.*;
import de.agilecoders.wicket.webjars.WicketWebjars;
import de.agilecoders.wicket.webjars.settings.WebjarsSettings;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.wicketstuff.htmlcompressor.HtmlCompressingMarkupFactory;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 *
 * @see page.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends WebPage> getHomePage() {
        return HandsOn09.class;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();

        // add your configuration here

        // サーバ・クライアント間のリクエスト・レスポンスの文字エンコード
        getRequestCycleSettings().setResponseRequestEncoding("UTF-8");
        // Wicketに取り込まれるHTMLファイルのエンコード
        getMarkupSettings().setDefaultMarkupEncoding("UTF-8");

        if (usesDeploymentConfig()) {
            getMarkupSettings().setMarkupFactory(new HtmlCompressingMarkupFactory());
        }

        getDebugSettings().setAjaxDebugModeEnabled(false);

        WicketWebjars.install(this, new WebjarsSettings());
    }
}
