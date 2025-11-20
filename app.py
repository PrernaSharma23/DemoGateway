from fastapi import FastAPI
from controller.gateway_controller import router as gateway_router
from controller.health_controller import router as health_router

app = FastAPI(title="Gateway Microservice")

app.include_router(gateway_router, prefix="/gateway", tags=["Gateway"])
app.include_router(health_router, prefix="", tags=["Health"])
