import {Component, OnInit} from '@angular/core';
import {BancoService} from './services/banco.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  saldoDisponible;
  title = 'cajero-front';

  constructor(private bancoService: BancoService) {
    this.bancoService.getSaldoDisponible().subscribe(resp => {
      this.saldoDisponible = resp;
    });
  }

  ngOnInit() {
  }
}
