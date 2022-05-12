<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class PouleController extends AbstractController
{
    /**
     * @Route("/poule", name="app_poule")
     */
    public function index(): Response
    {
        return $this->render('poule/index.html.twig', [
            'controller_name' => 'PouleController',
        ]);

    }

    /**
     * @Route("/pouleback", name="app_pouleback")
     */
    public function indexback(): Response
    {
        return $this->render('poule/pouleback.html.twig', [
            'controller_name' => 'PouleController',
        ]);
    }
}
