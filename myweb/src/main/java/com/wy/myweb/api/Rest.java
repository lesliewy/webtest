package com.wy.myweb.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.wy.myweb.object.RestResult;

/**
 * Created by leslie on 2018/3/12.
 */
@Path("/myweb")
@Produces({ "application/json; charset=UTF-8" })
@Consumes({ "application/x-www-form-urlencoded; charset=UTF-8" })
public interface Rest {

    @GET
    @Path("/query")
    RestResult query();
}
