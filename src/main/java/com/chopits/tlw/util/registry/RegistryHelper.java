package com.chopits.tlw.util.registry;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.function.Supplier;

import static com.chopits.tlw.TheLostWorld.MOD_ID;

public class RegistryHelper<T> {

    //TODO--容量待定
    private final HashSet<RegistryObject<? extends T>> elements = new HashSet<>(100,0.75F);
    private final Registry<T> registry;

    public RegistryHelper(Registry<T> registry){
        this.registry = registry;
    }

    public void registerAll(){
        for (RegistryObject<? extends T> itemRegistryObject :
                elements){
            Registry.register(registry,new Identifier(MOD_ID, itemRegistryObject.getPath()), itemRegistryObject.getObject());
        }
    }

    public RegistryObject<T> register(String path, final @NotNull Supplier<? extends T> element){
        RegistryObject<T> object = new RegistryObject<>(path, element.get());
        this.elements.add(object);
        return object;
    }

    public record RegistryObject<T>(String path, T object) {

        public T getObject() {
            return object;
        }

        public String getPath() {
            return path;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RegistryObject<?> that = (RegistryObject<?>) o;
            return getPath().equals(that.getPath()) && getObject().equals(that.getObject());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getPath(), getObject());
        }

        @Contract(pure = true)
        @Override
        public @NotNull String toString() {
            return "RegistryObject{" +
                    "path='" + path + '\'' +
                    '}';
        }
    }
}
