# ☁️ Weather App Android
Aplicativo Android desenvolvido em Java para visualização de dados meteorológicos em tempo real e gerenciamento de cidades favoritas, integrando com Weather API.

## 🚀 Tecnologias Utilizadas
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Retrofit](https://img.shields.io/badge/Retrofit-48B983?style=for-the-badge&logo=square&logoColor=white)
![Room](https://img.shields.io/badge/Room-3DDC84?style=for-the-badge&logo=sqlite&logoColor=white)
![Hilt](https://img.shields.io/badge/Hilt-2196F3?style=for-the-badge&logo=dagger&logoColor=white)
![Material Design](https://img.shields.io/badge/Material_Design-757575?style=for-the-badge&logo=material-design&logoColor=white)

## 📋 Descrição
Aplicativo Android nativo que permite consultar dados meteorológicos em tempo real e gerenciar uma lista de cidades favoritas. Desenvolvido seguindo as melhores práticas de arquitetura e UI/UX.

## 🛠️ Funcionalidades
- ✅ Consulta de dados meteorológicos em tempo real
- ✅ Gerenciamento de cidades favoritas
- ✅ Cache local para funcionamento offline
- ✅ UI com Material Design

## 🏗️ Arquitetura
O aplicativo segue a arquitetura Clean Architecture + MVVM:
- **data**: Camada de dados
  - remote: API e DTOs
  - local: Room Database e Entities
  - repository: Implementações dos repositórios
- **domain**: Regras de negócio
  - model: Modelos de domínio
  - repository: Interfaces dos repositórios
  - usecase: Casos de uso
- **di**: Injeção de dependência com Hilt
- **presentation**: UI e ViewModels
  - ui: Activities e Fragments
  - viewmodel: ViewModels e estados

## ⚙️ Configuração Local
1. Clone o repositório
```bash
git clone https://github.com/SEU_USUARIO/weather-app.git
```

2. Configure a API local
```bash
# Execute a Weather API em localhost:8080
```

3. Execute o projeto
```bash
# Abra no Android Studio e execute
```

## 📦 Principais Dependências
- Retrofit para comunicação com API
- Room para persistência local
- Hilt para injeção de dependência
- Navigation Component para navegação
- Material Design Components
- LiveData e ViewModel
- Gson para serialização JSON

## 🔒 Tratamento de Erros
- Cache local para funcionamento offline
- Tratamento de erros de rede
- Feedback visual para o usuário
- Validação de entrada de dados

## 🎨 UI/UX
- Material Design Guidelines
- Animações e transições suaves
- Feedback visual imediato

## 📈 Próximos Passos
- [ ] Implementação de testes unitários
- [ ] Testes de UI com Espresso
- [ ] Widgets para tela inicial
- [ ] Notificações de alertas climáticos

## 👨‍💻 Autor
[Guilherme Carneiro](https://github.com/guicarneiro11) - [@guizaokt](https://twitter.com/seu_twitter)

## 🤝 Integração
Este aplicativo é parte do ecossistema Weather App:
- [Weather API](https://github.com/guicarneiro11/weather-api)

---
