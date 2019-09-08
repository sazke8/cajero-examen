import {Component, OnInit} from '@angular/core';
import {BancoService} from '../../services/banco.service';

@Component({
  selector: 'app-sugerencia',
  templateUrl: './sugerencia.component.html',
  styleUrls: ['./sugerencia.component.css']
})
export class SugerenciaComponent implements OnInit {
  total: number;
  cambio: any;
  saldoDisponible: any;

  constructor(private bancoService: BancoService) {
    this.consultarSaldoDisponible();
  }

  ngOnInit() {
  }

  retirar(cantidad: number) {
    this.bancoService.retirarEfectivo(cantidad).subscribe(resp => {
      this.cambio = resp;
      this.calcularTotal(resp);
      this.consultarSaldoDisponible();
    });
  }

  calcularTotal(resp) {
    this.total = resp.reduce((prev, curr) => prev + (curr.denominacion * curr.cantidad), 0);
  }

  consultarSaldoDisponible() {
    this.bancoService.getSaldoDisponible().subscribe(resp => {
      this.saldoDisponible = resp;
    });
  }

}
