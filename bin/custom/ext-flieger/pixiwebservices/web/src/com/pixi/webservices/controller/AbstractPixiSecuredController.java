package com.pixi.webservices.controller;

import org.springframework.security.access.annotation.Secured;

@Secured({"ROLE_PIXIGROUP"})
public class AbstractPixiSecuredController extends AbstractPixiController {
}