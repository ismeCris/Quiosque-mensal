package view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class GraficoComJFreeChart extends JFrame {

    public GraficoComJFreeChart() {
        super("Gráfico de Barras com JFreeChart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Cria o conjunto de dados para o gráfico
        CategoryDataset dataset = createDataset();

        // Cria o gráfico de barras
        JFreeChart chart = createChart(dataset);

        // Cria um painel de gráfico para exibir o gráfico
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 400));

        // Adiciona o painel de gráfico ao JFrame
        setContentPane(chartPanel);
    }

    // Método para criar o conjunto de dados para o gráfico
    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Adiciona dados fictícios (quantidade de reservas por mês)
        dataset.addValue(10, "Reservas", "Janeiro");
        dataset.addValue(15, "Reservas", "Fevereiro");
        dataset.addValue(8, "Reservas", "Março");
        dataset.addValue(12, "Reservas", "Abril");
        dataset.addValue(18, "Reservas", "Maio");

        return dataset;
    }

    // Método para criar o gráfico de barras
    private JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Reservas por Mês",         // Título do gráfico
                "Mês",                      // Rótulo do eixo X
                "Quantidade",               // Rótulo do eixo Y
                dataset,                    // Dados para o gráfico
                PlotOrientation.VERTICAL,   // Orientação do gráfico
                true,                       // Incluir legenda
                true,                       // Incluir dicas de ferramentas
                false                       // URLs?
        );

        // Configuração adicional do gráfico (cores, fontes, etc.)
        chart.setBackgroundPaint(Color.white);

        return chart;
    }

    // Método principal para iniciar o aplicativo Swing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GraficoComJFreeChart grafico = new GraficoComJFreeChart();
            grafico.setVisible(true);
        });
    }
}
