package com.foodtruck.procurement.application;

/**
 * Outbound-Port zum externen System "MultiRoute Tour!".
 * Die Implementierung (Adapter) liegt in der Infrastruktur-Schicht.
 */
public interface MultiRouteTourGateway {

    MultiRouteDispatchResult dispatch(MultiRouteDispatchCommand command);
}
