package answer;

import answer.service.IContributionService;
import com.google.inject.Inject;
import de.agilecoders.wicket.webjars.request.resource.WebjarsCssResourceReference;
import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;
import lombok.val;
import net.arnx.jsonic.JSON;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import static org.apache.wicket.ajax.attributes.CallbackParameter.explicit;

public class HandsOn13 extends WebPage {
    private static final long serialVersionUID = 2947674758440633002L;

    @Inject
    private IContributionService contributionService;

    public HandsOn13(){
        IModel<String> latLngModel = new Model<>();

        add(new AjaxLink<Void>("addMarker") {
            private static final long serialVersionUID = -3557070478203422027L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                target.appendJavaScript("setMarker("+ JSON.encode("吹き出しの中身") +");");
            }
        });

        add(new AjaxLink<Void>("deleteMarker") {
            private static final long serialVersionUID = 6980420161575158161L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                target.appendJavaScript("deleteMarker();");
            }
        });

        WebMarkupContainer latLngWMC = new WebMarkupContainer("latLngWMC"){

            private static final long serialVersionUID = -2789836514770760188L;

            @Override
            protected void onInitialize() {
                super.onInitialize();
                setOutputMarkupId(true);
            }
        };
        add(latLngWMC);
        latLngWMC.add(new Label("latLng", latLngModel));

        add(new AbstractDefaultAjaxBehavior() {
            private static final long serialVersionUID = -488243388522526746L;

            @Override
            public void renderHead(Component component, IHeaderResponse response) {
                super.renderHead(component, response);
                val function = getCallbackFunction(explicit("PARAM_lat"),explicit("PARAM_lon"));
                val js = "sendLatLon = " + function.toString();
                response.render(OnDomReadyHeaderItem.forScript(js));
            }

            @Override
            protected void respond(AjaxRequestTarget target) {
                val latitude = getRequest().getRequestParameters().getParameterValue("PARAM_lat").toDouble(0d);
                val longitude = getRequest().getRequestParameters().getParameterValue("PARAM_lon").toDouble(0d);
                latLngModel.setObject("lat:" + latitude + ", lon:" + longitude);
                target.add(latLngWMC);
            }
        });
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(JavaScriptHeaderItem.forReference(getApplication().getJavaScriptLibrarySettings().getJQueryReference()));
        response.render(JavaScriptHeaderItem.forReference(new WebjarsJavaScriptResourceReference("./leaflet/current/dist/leaflet.js")));
        response.render(CssHeaderItem.forReference(new WebjarsCssResourceReference("./leaflet/current/dist/leaflet.css")));
        response.render(JavaScriptHeaderItem.forUrl("./js/leaflet.extra-markers.min.js"));
        response.render(CssHeaderItem.forUrl("./css/leaflet.extra-markers.min.css"));
        response.render(JavaScriptHeaderItem.forUrl("./answer/HandsOn13.js"));
        response.render(OnDomReadyHeaderItem.forScript("drawMap();"));
        response.render(OnDomReadyHeaderItem.forScript("setContributionMarker("+ JSON.encode(contributionService.selectContributionList()) +");"));
    }
}