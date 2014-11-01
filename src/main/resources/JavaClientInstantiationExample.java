package main.resources;

import io.sphere.client.model.SearchResult;
import io.sphere.client.shop.SphereClient;
import io.sphere.client.shop.SphereClientConfig;
import io.sphere.client.shop.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

public class JavaClientInstantiationExample {

    private SphereClient sphere;
    private static String result = null;

    public void init() throws Exception {

        final Map<String, Object> values;
        values = new HashMap<>();
        values.put("sphere.project", "lamas-mit-huten-92");
        values.put("sphere.clientId", "W-AHAvhGtj-N19oqHPOeKjao");
        values.put("sphere.clientSecret", "AMyWNNvV79LcZl2lh6YUk6XwWPfSDqkV");

        SphereClientConfig scc = new SphereClientConfig.Builder(values.get(
                "sphere.project").toString(), values.get("sphere.clientId")
                .toString(), values.get("sphere.clientSecret").toString(),
                Locale.ENGLISH).build();

        sphere = SphereClient.create(scc);

    }

    public int getAllProductsAndPrint() throws InterruptedException,
            ExecutionException {

        ListenableFuture<SearchResult<Product>> products = sphere.products()
                .all().fetchAsync();

        SearchResult<Product> list = products.get();

        List<Product> results = list.getResults();

        for (Product p : results) {
            System.out.println(p.getName() + " : " + p.getId());
        }

        return list.getCount();
    }

    public String getProductByIdAsync(final String id) {

        Futures.addCallback(sphere.products().byId(id).fetchAsync(),
                new FutureCallback<Optional<Product>>() {
                    @Override
                    public void onSuccess(Optional<Product> p) {
                        if (p.isPresent()) {
                            Product product = p.get();
                            result = product.getName();

                        } else {
                            // product was not found
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        result = "no product to id: " + id + " found";
                        // unexpected error, such as a 500 response from the
                        // backend, or
                        // connection timeout
                    }
                });

        return result;
    }

    public void endSession() {
        sphere.shutdown();
    }

}
