import time
import httpx
from typing import Dict

START_TIME = time.time()
VERSION = "1.0.0-gateway"
SERVER_URL = "http://localhost:8002"

async def _check_server() -> Dict:
    try:
        async with httpx.AsyncClient(timeout=2.0) as client:
            resp = await client.get(f"{SERVER_URL}/health")
            if resp.status_code == 200:
                return {"server": "ok"}
            else:
                return {"server": f"error:{resp.status_code}"}
    except Exception as e:
        return {"server": f"unreachable:{str(e)}"}

def _uptime_seconds() -> float:
    return time.time() - START_TIME

async def get_health() -> Dict:
    server_status = await _check_server()
    return {
        "service": "gateway",
        "status": "ok" if server_status.get("server") == "ok" else "degraded",
        "version": VERSION,
        "uptime_seconds": round(_uptime_seconds(), 2),
        "dependencies": server_status
    }
