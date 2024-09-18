package com.example.geradorarquivos.application.ports.out;

import com.example.geradorarquivos.domain.entities.Layout;

public interface LayoutRepository {
    Layout obterLayout(String nomeLayout);
}
