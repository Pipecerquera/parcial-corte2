<template>
    <ion-list>
      <ion-item v-for="invoice in invoices" :key="invoice.id">
        <ion-label>
          <h2>Cliente: {{ invoice.customer }}</h2>
          <p>Método de pago: {{ invoice.paymentMethod }}</p>
          <p>Total: ${{ invoice.total }}</p>
          <p>Productos: {{ formatProductsList(invoice.products) }}</p>
        </ion-label>
      </ion-item>
    </ion-list>
  </template>
  
  <script setup lang="ts">
  import { defineProps } from 'vue';
  
  // Definición de tipos
  interface Product {
    [key: string]: any;  // Permite cualquier propiedad en el objeto producto
    id?: number | string;
    name?: string;
    productName?: string;
    title?: string;
    description?: string;
  }
  
  interface Invoice {
    id?: number | string;
    customer: string;
    paymentMethod: string;
    total: number;
    products?: Product[] | null;
  }
  
  const props = defineProps<{
    invoices: Invoice[]
  }>();
  
  // Función para obtener el nombre del producto con tipado adecuado
  function formatProductsList(products: Product[] | null | undefined): string {
    if (!products || !Array.isArray(products) || products.length === 0) {
      return 'Sin productos';
    }
    
    return products.map((p: Product | string): string => {
      // Si el producto es solo un string
      if (typeof p === 'string') return p;
      if (!p) return 'Producto desconocido';
      
      // Buscar la primera propiedad que podría ser un nombre
      const nameProperty = p.name || p.productName || p.title || 
                           p.description || (p as any).producto || (p as any).nombreProducto;
      
      if (nameProperty) return nameProperty;
      
      // Si no encontramos ninguna propiedad específica, usar el ID
      if (p.id) return `Producto #${p.id}`;
      
      // Como último recurso, mostrar un resumen del objeto
      return `Producto (${Object.keys(p).join(', ')})`;
    }).join(', ');
  }
  </script>