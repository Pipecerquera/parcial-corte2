<template>
    <ion-list>
      <ion-item v-for="product in products" :key="product.id">
        <ion-label>
          <h2>{{ product.name }}</h2>
          <p>Precio: ${{ product.price }}</p>
        </ion-label>
        <ion-button slot="end" @click="$emit('add-to-cart', product)">Agregar</ion-button>
      </ion-item>
    </ion-list>
  </template>
  
  <script setup lang="ts">
  const products = [
    { id: 1, name: 'Camisa', price: 50000 },
    { id: 2, name: 'Pantalón', price: 80000 }
  ]
  
  defineEmits(['add-to-cart'])
  </script>
  