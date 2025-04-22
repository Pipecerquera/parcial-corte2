<template>
  <ion-page>
    <invoice-header :customer-name="customerName" />

    <ion-content class="ion-padding">
      <!-- Lista de productos manuales -->
      <product-list :products="products" @add-to-cart="addToCart" />
      
      <!-- Mensaje de error si no hay productos cargados -->
      <div v-if="products.length === 0" class="error-message">
        ❌ No se han definido productos. Por favor, agrega algunos productos manualmente.
      </div>

      <payment-methods @payment-selected="setPaymentMethod" />

      <ion-card>
        <ion-card-header>
          <ion-card-title>Resumen de Compra</ion-card-title>
        </ion-card-header>
        <ion-card-content>
          <p>Total de productos: {{ cart.length }}</p>
          <p>Total a pagar: ${{ total }}</p>
          <p>Método de pago: {{ paymentMethod }}</p>
        </ion-card-content>
      </ion-card>

      <ion-button expand="full" color="success" @click="enviarFactura">
        Enviar Factura
      </ion-button>

      <ion-button router-link="/facturas" expand="full" color="medium">
        Ver Facturas
      </ion-button>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import ProductList from '@/components/ProductList.vue'
import PaymentMethods from '@/components/PaymentMethods.vue'
import InvoiceHeader from '@/components/InvoiceHeader.vue'
import { ref, computed } from 'vue'

const customerName = 'Juan Pérez'
const cart = ref<any[]>([])
const paymentMethod = ref('Efectivo')

// Definición de productos manualmente
const products = ref<any[]>([
  { id: 1, name: 'Camisa', price: 100.000 },
  { id: 2, name: 'Reloj', price: 200.000 },
  { id: 3, name: 'zapatos', price: 300.000 },
  { id: 4, name: 'Pantalone', price: 400.000 }
])

function addToCart(product: any) {
  cart.value.push(product)
}

function setPaymentMethod(method: string) {
  paymentMethod.value = method
}

const total = computed(() => cart.value.reduce((sum, p) => sum + p.price, 0))

async function enviarFactura() {
  if (cart.value.length === 0) {
    alert('❌ No hay productos en el carrito')
    return
  }

  const factura = {
    customer: customerName,
    paymentMethod: paymentMethod.value,
    total: total.value,
    products: cart.value
  }

  try {
    const res = await fetch('http://localhost:8080/api/invoices', {
      method: 'POST',
      headers: { 
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      },
      body: JSON.stringify(factura)
    })

    if (res.ok) {
      alert('✅ Factura enviada correctamente')
      cart.value = []
    } else {
      const errorText = await res.text()
      console.error('Respuesta del servidor:', errorText)
      alert(`❌ Error del servidor: ${res.status} ${res.statusText}`)
    }
  } catch (error) {
    console.error('Error al enviar factura:', error)
    alert('❌ Error de conexión. Verifique que el servidor esté activo en localhost:8080')
  }
}

</script>

<style scoped>
.error-message {
  color: red;
  font-weight: bold;
}
</style>
