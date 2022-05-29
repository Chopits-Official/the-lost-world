package com.chopits.tlw.util.registry;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.function.Supplier;

public class RegistryHelper<T> {
    private final Registry<T> registry;
    private final String namespace;
    private final LinkedList<RegistryObject<T>> registryObjects = new LinkedList<>();

    public RegistryHelper(Registry<T> registry, String namespace) {
        this.registry = registry;
        this.namespace = namespace;
    }

    public void registryObj(RegistryObject<T> object) {
        this.registryObjects.add(object);
    }

    public void registry() {
        for (RegistryObject<T> obj :
                registryObjects) {
            Registry.register(this.registry, new Identifier(this.namespace, obj.id), obj.object);
        }
    }

    public static class RegistryObject<T> {
        private final T object;
        private final String id;
        private RegistryObject(@NotNull Supplier<? extends T> object, String id) {
            this.object = object.get();
            this.id = id;
        }
        public RegistryObject<T> registry(@NotNull RegistryHelper<T> registry) {
            registry.registryObj(this);
            return this;
        }
    }
}
