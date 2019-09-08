import {Component, OnInit} from '@angular/core';
import {BancoService} from '../../services/banco.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-custom',
  templateUrl: './custom.component.html',
  styleUrls: ['./custom.component.css']
})
export class CustomComponent implements OnInit {

  customForm: FormGroup;
  cambio: any;
  total: number;
  saldoDisponible: any;

  constructor(private bancoService: BancoService, private formBuilder: FormBuilder) {
    this.consultarSaldoDisponible();
  }

  ngOnInit() {
    this.customForm = this.formBuilder.group({
      cantidadEspecifica: ['', Validators.required]
    });
  }

  retirar() {
    this.bancoService.retirarEfectivo(this.customForm.controls.cantidadEspecifica.value).subscribe(resp => {
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
