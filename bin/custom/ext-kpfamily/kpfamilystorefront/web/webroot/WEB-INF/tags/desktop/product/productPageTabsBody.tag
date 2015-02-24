<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true"
        type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="col-md-6 col-sm-8 tab150119">    
    <div class="tab-content">
        <div id="sectionA" class="tab-pane fade in active">
            <p>
                ${product.description}
            </p>
        </div>
        <div id="sectionB" class="tab-pane fade">
            <p>
                <!-- Reviews -->
            </p>
        </div>
        <div id="sectionC" class="tab-pane fade">
            <p>
                <!-- Technical Details -->
            </p>
        </div>
        <div id="dropdown1" class="tab-pane fade">
            <p>
                <!-- Q&A -->
            </p>
        </div>
        <div id="dropdown2" class="tab-pane fade">
            <p>
                <!-- Downloads -->
            </p>
        </div>
        <div id="dropdown3" class="tab-pane fade">
            <p>
                <!-- Accessories -->
            </p>
        </div>
    </div>
</div>