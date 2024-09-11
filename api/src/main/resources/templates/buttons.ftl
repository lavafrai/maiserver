<#macro button href="" icon="" icon_type="regular" mobile="false" modal="">
    <a class="button button-primary <#if mobile=="true">button-mobile</#if>" href="${href}" <#if modal?has_content>data-hystmodal="${modal}"</#if>>
        <#if (icon?has_content)>
            <i class="fa-${icon_type} fa-${icon}"></i>
        </#if>
        <span class="button-name"><#nested /></span>
    </a>
</#macro>

<#macro button_dark href="" icon="" icon_type="regular" mobile="false">
    <a class="button button-dark <#if mobile=="true">button-mobile</#if>" href="${href}">
        <#if (icon?has_content)>
            <i class="fa-${icon_type} fa-${icon}"></i>
        </#if>
        <span class="button-name"><#nested /></span>
    </a>
</#macro>